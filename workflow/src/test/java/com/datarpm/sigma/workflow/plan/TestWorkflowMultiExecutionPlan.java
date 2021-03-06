/*******************************************************************************
 *   Licensed to the Apache Software Foundation (ASF) under one or more
 *   contributor license agreements.  See the NOTICE file distributed with
 *   this work for additional information regarding copyright ownership.
 *   The ASF licenses this file to You under the Apache License, Version 2.0
 *   (the "License"); you may not use this file except in compliance with
 *   the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.datarpm.sigma.workflow.plan;

import java.util.concurrent.atomic.AtomicBoolean;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import com.datarpm.sigma.workflow.WorkflowContextFactory;
import com.datarpm.sigma.workflow.WorkflowEngine;
import com.datarpm.sigma.workflow.WorkflowEngineConfig;
import com.datarpm.sigma.workflow.WorkflowExecutionPlan;
import com.datarpm.sigma.workflow.WorkflowState;
import com.datarpm.sigma.workflow.WorkflowStateException;
import com.datarpm.sigma.workflow.WorkflowExecutionPlan.Builder;

/**
 * @author vishal
 * 
 */
public class TestWorkflowMultiExecutionPlan extends TestCase {

	private static final boolean EXECUTED = true;
	private static final boolean FALSE = false;

	@Before
	public void initialize() {
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSimpleWorkflow() throws Exception {

		SimpleWorkflowRequest simpleRequest = new SimpleWorkflowRequest();
		Builder<SimpleWorkflowRequest, SimpleWorkflowContext> builder = null;
		WorkflowContextFactory<SimpleWorkflowRequest, SimpleWorkflowContext> contextFactory = new WorkflowContextFactory<SimpleWorkflowRequest, SimpleWorkflowContext>() {
			@Override
			public SimpleWorkflowContext create() {
				return new SimpleWorkflowContext();
			}
		};

		builder = new WorkflowExecutionPlan.Builder<SimpleWorkflowRequest, SimpleWorkflowContext>(
				simpleRequest, contextFactory);
		builder.executeState(new FirstState())
				.executeConcurrent(new ConcurrentState("T1"),
						new ConcurrentState("T2"))
				.executeState(new NextState());

		WorkflowEngineConfig config = new WorkflowEngineConfig();
		config.setWorkerThreadCount(2);
    SimpleWorkflowContext workflowContext = new WorkflowEngine(config)
				.execute(builder.getPlan());

		assertEquals(workflowContext.isFailed(), FALSE);
		assertEquals(workflowContext.isState1Executed(), EXECUTED);
		assertEquals(workflowContext.getSharedFlag().get(), EXECUTED);
		assertEquals(workflowContext.isState2Executed(), EXECUTED);
	}

	class SimpleWorkflowRequest {

	}

	public static class FirstState implements
			WorkflowState<SimpleWorkflowRequest, SimpleWorkflowContext> {

		@Override
		public void execute(SimpleWorkflowContext context)
				throws WorkflowStateException {
			System.out.println("State 1 Executed");
			context.setState1Executed(EXECUTED);
		}

	}

	public static class NextState implements
			WorkflowState<SimpleWorkflowRequest, SimpleWorkflowContext> {

		@Override
		public void execute(SimpleWorkflowContext context)
				throws WorkflowStateException {
			System.out
					.println("State 2 Executed after State 1 and concurrent state execution");
			if (context.isState1Executed() && context.getSharedFlag().get())
				context.setState2Executed(EXECUTED);
		}

	}

	public static class ConcurrentState implements
			WorkflowState<SimpleWorkflowRequest, SimpleWorkflowContext> {

		private String name;

		public ConcurrentState(String name) {
			this.name = name;
		}

		@Override
		public void execute(SimpleWorkflowContext context)
				throws WorkflowStateException {
			System.out.println("State " + name + " Executed  Flag : "
					+ context.getSharedFlag().get());
			context.getSharedFlag().set(EXECUTED);
		}

	}

	static class SimpleWorkflowContext extends
			com.datarpm.sigma.workflow.WorkflowContext<SimpleWorkflowRequest> {
		private static final long serialVersionUID = 1L;

		private boolean state1Executed = false;
		private boolean state2Executed = false;
		private AtomicBoolean sharedFlag = new AtomicBoolean(false);

		boolean isState1Executed() {
			return state1Executed;
		}

		void setState1Executed(boolean state1Executed) {
			this.state1Executed = state1Executed;
		}

		boolean isState2Executed() {
			return state2Executed;
		}

		void setState2Executed(boolean state2Executed) {
			this.state2Executed = state2Executed;
		}

		AtomicBoolean getSharedFlag() {
			return sharedFlag;
		}

	}
}
