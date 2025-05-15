package kpi.lab2.model.service;

import kpi.lab2.model.dao.DaoConnection;
import kpi.lab2.model.dao.DaoFactory;
import kpi.lab2.model.dao.CarDao;
import kpi.lab2.model.entity.Car;
import kpi.lab2.model.service.exception.ServiceException;
import kpi.lab2.utils.ErrorsMessages;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class CarService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public static final String SERVICE_ERROR_CAR_EXIST =
            "Car with such model ('%s') already exist";

    private static class Holder{
        static final CarService INSTANCE = new CarService();
    }

    public static CarService getInstance(){
        return Holder.INSTANCE;
    }

    public void create(Car car) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarDao dao = daoFactory.createCarDao(connection);
            connection.begin();
            Optional<Car> existingCar = dao.findByModel(car.getModel());
            checkIfCarAlreadyExist(existingCar);
            dao.create(car);
            connection.commit();
        }
    }

    public void update(Car car) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarDao dao = daoFactory.createCarDao(connection);
            connection.begin();
            Optional<Car> existingCar = dao.findByModel(car.getModel());
            existingCar.ifPresent(existingCar1 -> {
                if (!Objects.equals(car.getId(), existingCar1.getId())) {
                    checkIfCarAlreadyExist(existingCar);
                }
            });
            dao.update(car);
            connection.commit();
        }
    }

    public void delete(int carId) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarDao dao = daoFactory.createCarDao(connection);
            dao.delete(carId);
        }
    }

    public Optional<Car> getById(Integer id) {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarDao dao = daoFactory.createCarDao(connection);
            return dao.find(id);
        }
    }

    public List<Car> getAll() {
        try( DaoConnection connection = daoFactory.getConnection() ){
            CarDao carDao = daoFactory.createCarDao(connection);
            return carDao.findAll();
        }
    }

    private void checkIfCarAlreadyExist(Optional<Car> existingCar) {
        existingCar.ifPresent(car -> {
            throw new ServiceException(
                    ErrorsMessages.SERVICE_ERROR_CAR_EXIST)
                    .addLogMessage(String
                            .format(SERVICE_ERROR_CAR_EXIST, car.getModel()));
        });
    }
}
