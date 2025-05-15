package kpi.lab2.model.dao.jdbc;

import kpi.lab2.model.dao.ManufacturerDao;
import kpi.lab2.model.entity.Manufacturer;


import java.sql.*;
import java.util.List;

public class JdbcManufacturerDao extends JdbcAbstract<Manufacturer> implements ManufacturerDao {
    private static final String DELETE_MANUFACTURER_BY_ID =
            "DELETE FROM country WHERE manufacturer_id = ? ";
    private static final String INSERT_INTO_MANUFACTURERS = "INSERT INTO " +
            "manufacturers (name, country)" +
            " VALUES ( ? , ?) ";
    private static final String SELECT_FROM_MANUFACTURERS = "SELECT * FROM manufacturers ";
    private static final String ORDER_BY_ID = "ORDER BY manufacturers.manufacturer_id ";
    private static final String WHERE_ID = "WHERE manufacturer_id = ? ";

    private static final String ID = "manufacturer_id";
    private static final String NAME = "name";
    private static final String COUNTRY = "country";

    public JdbcManufacturerDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_MANUFACTURERS + ORDER_BY_ID;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_MANUFACTURERS;
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getDeleteQuery() {
        return DELETE_MANUFACTURER_BY_ID;
    }

    @Override
    protected Manufacturer getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getManufacturerFromResultSet(resultSet);
    }

    static Manufacturer getManufacturerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Manufacturer.Builder()
                .setId(resultSet.getInt(ID))
                .setName(resultSet.getString(NAME))
                .setCountry(resultSet.getString(COUNTRY))
                .build();
    }

    @Override
    protected void setIdForEntity(Manufacturer entity, int id) {
        entity.setId(id);
    }


    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Manufacturer entity)
            throws SQLException {
        query.setString(1 , entity.getName());
        query.setString(2 , entity.getCountry());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Manufacturer entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_MANUFACTURERS + WHERE_ID;
    }

    @Override
    public List<Manufacturer> findByName(String name) {
        throw new UnsupportedOperationException();
    }
}
