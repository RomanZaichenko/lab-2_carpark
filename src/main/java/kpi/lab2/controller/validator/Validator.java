package kpi.lab2.controller.validator;

public interface Validator<E> {
    boolean validate(E t, Errors errors);
    boolean validate(E t);
}