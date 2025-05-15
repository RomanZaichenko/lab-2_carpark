package kpi.lab2.model.dao.jdbc;

import kpi.lab2.model.dao.*;
import kpi.lab2.model.dao.exception.DaoException;
import kpi.lab2.model.entity.Manufacturer;

import javax.sql.DataSource;
import javax.naming.InitialContext;
import java.sql.*;

public class JdbcDaoFactory extends DaoFactory {
    private final DataSource dataSource;

    public JdbcDaoFactory() {
        try{
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/users_db");

        }catch(Exception e){
            throw new DaoException(e);
        }
    }

    @Override
    public DaoConnection getConnection() {
        try {
            return new JdbcDaoConnection(dataSource.getConnection());
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public ManufacturerDao createManufacturerDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcManufacturerDao(sqlConnection);
    }

    @Override
    public CarDao createCarDao(DaoConnection daoConnection) {
        JdbcDaoConnection jdbcConnection = (JdbcDaoConnection) daoConnection;
        Connection sqlConnection = jdbcConnection.getConnection();
        return new JdbcCarDao(sqlConnection);
    }
}
