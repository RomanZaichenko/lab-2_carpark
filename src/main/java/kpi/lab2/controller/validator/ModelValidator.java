package kpi.lab2.controller.validator;

import kpi.lab2.utils.AttributesHolder;
import kpi.lab2.utils.ErrorsMessages;

import java.util.regex.Pattern;

public class ModelValidator implements Validator<String> {
    private final Pattern modelPattern;

    public ModelValidator() {
        modelPattern = Pattern.compile(RegExp.MODEL);
    }

    public ModelValidator(String modelRegex) {
        modelPattern = Pattern.compile(modelRegex);
    }

    @Override
    public boolean validate(String model, Errors errors) {
        if (!validate(model)) {
            errors.setResult(false);
            errors.addMessage(AttributesHolder.MODEL, ErrorsMessages.MODEL_INVALID);
            return errors.getResult();
        }
        return true;
    }

    @Override
    public boolean validate(String email) {
        return modelPattern.matcher(email).matches();
    }
}
