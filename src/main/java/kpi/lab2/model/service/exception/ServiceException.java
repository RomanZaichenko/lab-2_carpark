package kpi.lab2.model.service.exception;

import kpi.lab2.exception.ApplicationException;
import kpi.lab2.utils.ErrorsMessages;

public class ServiceException extends ApplicationException {
  public ServiceException(){
    super(ErrorsMessages.SERVICE_ERROR);
  }

  public ServiceException(Exception cause) {
    super(ErrorsMessages.SERVICE_ERROR, cause);
  }

  public ServiceException(String messageKey) {
    super(messageKey);
  }

  @Override
  public ServiceException addLogMessage(String logMessage) {
    super.addLogMessage(logMessage);
    return this;
  }
}
