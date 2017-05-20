/*******************************************************************************
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.datarpm.sigma.workflow.examples.simple.sql.states;

import com.datarpm.sigma.workflow.AddStateListner;
import com.datarpm.sigma.workflow.WorkflowState;
import com.datarpm.sigma.workflow.WorkflowStateException;
import com.datarpm.sigma.workflow.examples.simple.sql.DatabaseQueryContext;
import com.datarpm.sigma.workflow.examples.simple.sql.DatabaseQueryRequest;

/**
 * @author vinay
 *
 */
@AddStateListner(names = { TerminateExecutionListener.class })
public class PrepareSQLQuery implements WorkflowState<DatabaseQueryRequest, DatabaseQueryContext> {

  @Override
  public void execute(DatabaseQueryContext context) throws WorkflowStateException {
    DatabaseQueryRequest request = context.getRequest();
    String tableName = request.getTableName();
    StringBuilder strBuilder = new StringBuilder();
    strBuilder.append("select * from ");
    strBuilder.append(tableName);
    strBuilder.append(" limit 10");
    String query = strBuilder.toString();
    context.setSqlQuery(query);
  }
}
