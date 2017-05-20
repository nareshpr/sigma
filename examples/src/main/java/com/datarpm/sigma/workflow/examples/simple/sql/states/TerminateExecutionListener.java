package com.datarpm.sigma.workflow.examples.simple.sql.states;

import com.datarpm.sigma.workflow.DoNotExecuteStateException;
import com.datarpm.sigma.workflow.ExceptionMessageKeys;
import com.datarpm.sigma.workflow.ListnerExecutionFailureException;
import com.datarpm.sigma.workflow.TerminateExecutionException;
import com.datarpm.sigma.workflow.WorkflowStateListner;
import com.datarpm.sigma.workflow.examples.simple.sql.DatabaseQueryContext;
import com.datarpm.sigma.workflow.examples.simple.sql.DatabaseQueryRequest;

public class TerminateExecutionListener
    implements WorkflowStateListner<DatabaseQueryRequest, DatabaseQueryContext> {

  @Override
  public void before(DatabaseQueryContext context)
      throws DoNotExecuteStateException, ListnerExecutionFailureException {

    if (context.isTerminateStateExecution()) {
      throw new TerminateExecutionException(ExceptionMessageKeys.TERMINATE_STATE_EXECUTION);
    }
  }

  @Override
  public void after(DatabaseQueryContext context)
      throws ListnerExecutionFailureException {
  }
}
