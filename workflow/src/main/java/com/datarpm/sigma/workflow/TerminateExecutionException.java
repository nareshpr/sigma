package com.datarpm.sigma.workflow;

public class TerminateExecutionException extends DoNotExecuteStateException {

  private static final long serialVersionUID = 1L;

  public TerminateExecutionException() {
    super();
  }

  public TerminateExecutionException(String message, Throwable cause, boolean enableSuppression,
      boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public TerminateExecutionException(String message, Throwable cause) {
    super(message, cause);
  }

  public TerminateExecutionException(String message) {
    super(message);
  }

  public TerminateExecutionException(Throwable cause) {
    super(cause);
  }
}
