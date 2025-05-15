package kpi.lab2.controller.validator;

import kpi.lab2.model.entity.Car;
import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.ErrorsMessages;

import java.util.regex.Pattern;

public class CarValidator implements Validator<Car> {
    private final ModelValidator modelValidator;
    private final Pattern yearPattern;

    public CarValidator() {
        modelValidator = new ModelValidator();
        yearPattern = Pattern.compile(RegExp.YEAR);
    }

    @Override
    public boolean validate(Car car, Errors errors) {
        if (car != null) {
            if (!yearPattern.matcher(String.valueOf(car.getYear())).matches()) {
                reject(errors, AttributesHolder.YEAR, ErrorsMessages.YEAR_INVALID);
            }
            if (!yearPattern.matcher(String.valueOf(car.getKilometrage())).matches()) {
                reject(errors, AttributesHolder.KILOMETRAGE, ErrorsMessages.KILOMETRAGE_INVALID);
            }
            if (car.getManufacturer() == null) {
                reject(errors, AttributesHolder.MANUFACTURER, ErrorsMessages.MANUFACTURER_INVALID);
            }
            modelValidator.validate(car.getModel(), errors);
        } else {
            reject(errors, AttributesHolder.CAR, ErrorsMessages.INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(Car car) {
        return !((yearPattern.matcher(String.valueOf(car.getYear())).matches()) ||
                (yearPattern.matcher(String.valueOf(car.getKilometrage())).matches()) ||
                (car.getManufacturer() == null) ||
                (!modelValidator.validate(car.getModel())));
    }

    private void reject(Errors errors, String attribute, String message){
        errors.addMessage(attribute, message);
        errors.setResult(false);
    }
}
