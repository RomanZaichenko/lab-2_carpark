package kpi.lab2.controller.command;

import org.apache.log4j.Logger;
import kpi.lab2.controller.FrontController;
import kpi.lab2.model.service.CarService;
import kpi.lab2.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCar implements Command {
    private final CarService carService = CarService.getInstance();

    private static final Logger logger = Logger.getLogger(DeleteCar.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();
        int carId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
        logger.info("Deleting car " +  carId);
        carService.delete(carId);

        response.sendRedirect(PathsHolder.CARS);

        return FrontController.REDIRECT;
    }
}