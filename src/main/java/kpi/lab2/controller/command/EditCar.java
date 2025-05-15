package kpi.lab2.controller.command;

import kpi.lab2.controller.FrontController;
import kpi.lab2.controller.validator.Errors;
import kpi.lab2.controller.validator.CarValidator;
import kpi.lab2.controller.validator.Validator;
import kpi.lab2.model.entity.Manufacturer;
import kpi.lab2.model.entity.Car;
import kpi.lab2.model.service.ManufacturerService;
import kpi.lab2.model.service.CarService;
import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.PagesHolder;
import kpi.lab2.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditCar implements Command {
    private final ManufacturerService manufacturerService = ManufacturerService.getInstance();
    private final CarService carService = CarService.getInstance();
    private final Validator<Car> carValidator = new CarValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Car car = buildCar(request);
        Errors errors = new Errors();
        if (carValidator.validate(car, errors)) {
            carService.update(car);
            response.sendRedirect(PathsHolder.CARS);
            return FrontController.REDIRECT;
        }

        request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
        request.setAttribute(AttributesHolder.ERRORS, errors);

        List<Manufacturer> manufacturerList = manufacturerService.getAll();
        request.setAttribute(AttributesHolder.MANUFACTURERS, manufacturerList);
        request.setAttribute(AttributesHolder.CAR, car);
        request.setAttribute(AttributesHolder.MANUFACTURERS, request.getAttribute(AttributesHolder.MANUFACTURERS));
        return PagesHolder.CAR;
    }

    private Car buildCar(HttpServletRequest request) {
        int manufacturer_id = Integer.parseInt(request.getParameter(AttributesHolder.MANUFACTURER));
        return new Car.Builder()
                .setModel(request.getParameter(AttributesHolder.MODEL))
                .setKilometrage(Float.parseFloat(request.getParameter(AttributesHolder.KILOMETRAGE)))
                .setFuelConsumption(Float.parseFloat(request.getParameter(AttributesHolder.FUEL_CONSUMPTION)))
                .setYear(Integer.parseInt(request.getParameter(AttributesHolder.YEAR)))
                .setFuelCapacity(Float.parseFloat(request.getParameter(AttributesHolder.FUEL_CAPACITY)))
                .setManufacturer(GetManufacturer(request))
                .build();
    }


    private Manufacturer GetManufacturer(HttpServletRequest request) {
        Manufacturer manufacturer;
        try {
            int manufacturer_id = Integer.parseInt(request.getParameter(AttributesHolder.MANUFACTURER));
            manufacturer = new Manufacturer.Builder().setId(manufacturer_id).build();
        } catch (NumberFormatException e) {
            manufacturer = null;
        }
        return manufacturer;
    }
}
