package kpi.lab2.model.dao;

import kpi.lab2.model.entity.Manufacturer;

import java.util.List;

public interface ManufacturerDao extends GenericDao<Manufacturer> {
    List<Manufacturer> findByName(String name);
}
