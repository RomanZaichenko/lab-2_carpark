package kpi.lab2.controller.command;

import kpi.lab2.model.entity.Car;
import kpi.lab2.model.service.CarService;
import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetCars implements Command {
    private final CarService carService = CarService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Car> cars = carService.getAll();

        request.setAttribute(AttributesHolder.CARS, cars);

        return PagesHolder.CARS;
    }
}
