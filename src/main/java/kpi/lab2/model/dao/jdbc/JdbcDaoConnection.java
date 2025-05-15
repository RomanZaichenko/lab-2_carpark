package kpi.lab2.model.dao.jdbc;

import kpi.lab2.model.dao.DaoConnection;
import kpi.lab2.model.dao.exception.DaoException;

import java.sql.*;

public class JdbcDaoConnection implements DaoConnection {
    private Connection connection;
    private boolean inTransaction = false;

    public JdbcDaoConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void begin() {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void commit() {
        try {
            connection.commit();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void rollback() {
        try {
            connection.rollback();
            inTransaction = false;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() {
        if(inTransaction) {
            rollback();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    Connection getConnection() {
        return connection;
    }
}
