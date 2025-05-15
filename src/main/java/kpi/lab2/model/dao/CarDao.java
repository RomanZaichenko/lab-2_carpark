package kpi.lab2.model.dao;

import kpi.lab2.model.entity.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao extends GenericDao<Car> {
    List<Car> findByYear(int year);
    Optional<Car> findByModel(String model);
}
