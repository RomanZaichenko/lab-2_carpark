package kpi.lab2.model.dao.jdbc;

import kpi.lab2.model.dao.CarDao;
import kpi.lab2.model.entity.Manufacturer;
import kpi.lab2.model.entity.Car;


import java.sql.*;
import java.util.List;


public class JdbcCarDao extends JdbcAbstract<Car> implements CarDao {
    private static final String DELETE_CAR_BY_ID = "DELETE FROM cars WHERE id = ? ";
    private static final String INSERT_INTO_CARS = "INSERT INTO cars " +
            "(manufacturer_id, model, fuelConsumption, kilometrage, year, fuelCapacity) " +
            " VALUES ( ?, ?, ?, ?, ?, ? ) ";

    private static final String SELECT_FROM_CARS = "SELECT * FROM user ";
    private static final String WHERE_ID = "WHERE car_id = ? ";
    private static final String WHERE_MODEL = "WHERE model = ? ";

    private static final String UPDATE_CARS = "UPDATE cars " +
            "SET manufacturer_id = ?, model = ?, fuelConsumption = ?, kilometrage = ?, year = ?, fuelCapacity = ? " ;

    private static final String ID = "car_id";
    private static final String MANUFACTURER_ID = "manufacturer_id";
    private static final String MODEL = "model";
    private static final String FUEL_CONSUMPTION = "fuelConsumption";
    private static final String KILOMETRAGE = "kilometrage";
    private static final String YEAR = "year";
    private static final String FUEL_CAPACITY = "fuelCapacity";

    public JdbcCarDao(Connection connection) {
        super(connection);
    }

    @Override
    protected String getSelectAllQuery() {
        return SELECT_FROM_CARS;
    }

    @Override
    protected String getCreateQuery() {
        return INSERT_INTO_CARS;
    }

    @Override
    protected String getUpdateQuery() {
        return UPDATE_CARS + WHERE_ID;
    }
    @Override
    protected String getDeleteQuery() {
        return DELETE_CAR_BY_ID;
    }

    @Override
    protected Car getEntityFromResultSet(ResultSet resultSet) throws SQLException {
        return getCarFromResultSet(resultSet);
    }

    static Car getCarFromResultSet(ResultSet resultSet) throws SQLException {
        return new Car.Builder()
                .setId(resultSet.getInt(ID))
                .setManufacturer(new Manufacturer.Builder().setId(resultSet.getInt(MANUFACTURER_ID)).build())
                .setModel(resultSet.getString(MODEL))
                .setFuelConsumption(resultSet.getFloat(FUEL_CONSUMPTION))
                .setKilometrage(resultSet.getFloat(KILOMETRAGE))
                .setYear(resultSet.getInt(YEAR))
                .setFuelCapacity(resultSet.getFloat(FUEL_CAPACITY))
                .build();
    }

    @Override
    protected void setIdForEntity(Car entity, int id) {
        entity.setId(id);
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement query, Car entity)
            throws SQLException {
        query.setInt(1 , entity.getManufacturer().getId());
        query.setString(2 , entity.getModel());
        query.setFloat(3, entity.getFuelConsumption());
        query.setFloat(4, entity.getKilometrage());
        query.setInt(5, entity.getYear());
        query.setFloat(6, entity.getFuelCapacity());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement query, Car entity) throws SQLException {
        query.setInt(1 , entity.getManufacturer().getId());
        query.setString(2 , entity.getModel());
        query.setFloat(3, entity.getFuelConsumption());
        query.setFloat(4, entity.getKilometrage());
        query.setInt(5, entity.getYear());
        query.setFloat(6, entity.getFuelCapacity());
    }

    @Override
    protected String getSelectByIdQuery() {
        return SELECT_FROM_CARS + WHERE_ID;
    }

    @Override
    public List<Car> findByModel(String year) {
        throw new UnsupportedOperationException();
    }
}
