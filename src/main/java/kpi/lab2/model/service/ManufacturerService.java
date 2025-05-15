package kpi.lab2.model.service;

import kpi.lab2.model.dao.ManufacturerDao;
import kpi.lab2.model.dao.DaoConnection;
import kpi.lab2.model.dao.DaoFactory;
import kpi.lab2.model.entity.Manufacturer;

import java.util.List;

public class ManufacturerService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder{
        static final ManufacturerService INSTANCE = new ManufacturerService();
    }

    public static ManufacturerService getInstance(){
        return Holder.INSTANCE;
    }

    public List<Manufacturer> getAll() {
        try( DaoConnection connection = daoFactory.getConnection() ){
            ManufacturerDao manufacturerDao = daoFactory.createManufacturerDao(connection);
            return manufacturerDao.findAll();
        }
    }
}
