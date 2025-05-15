package kpi.lab2.model.dao.exception;

import kpi.lab2.exception.ApplicationException;
import kpi.lab2.utils.ErrorsMessages;

public class DaoException extends ApplicationException {

    public DaoException(){
        super(ErrorsMessages.DAO_ERROR);
    }

    public DaoException(Exception cause) {
        super(ErrorsMessages.DAO_ERROR, cause);
    }
}
