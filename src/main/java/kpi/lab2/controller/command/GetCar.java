package kpi.lab2.controller.command;

import kpi.lab2.model.entity.Manufacturer;
import kpi.lab2.model.entity.Car;
import kpi.lab2.model.service.ManufacturerService;
import kpi.lab2.model.service.CarService;
import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.PagesHolder;
import kpi.lab2.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetCar implements Command {
    private final ManufacturerService manufacturerService = ManufacturerService.getInstance();
    private final CarService carService = CarService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Manufacturer> manufacturerList = manufacturerService.getAll();
        request.setAttribute(AttributesHolder.MANUFACTURERS, manufacturerList);

        String path = request.getRequestURI();
        if (path.contains(PathsHolder.EDIT_CAR))
        {
            int carId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            Optional<Car> car = carService.getById(carId);
            car.ifPresent(car1 -> request.setAttribute(AttributesHolder.CAR, car1));
        }

        return PagesHolder.CAR;
    }
}