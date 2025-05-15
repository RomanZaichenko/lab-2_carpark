package kpi.lab2.model.dao;

import kpi.lab2.model.entity.Car;

import java.util.List;

public interface CarDao extends GenericDao<Car> {
    List<Car> findByModel(String model);
}
