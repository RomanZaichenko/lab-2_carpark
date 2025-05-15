package kpi.lab2.controller.validator;

public class RegExp {
    public static final String MODEL = "^[A-Za-z0-9 ]{6,25}$\n";
    public static final String NAME = "^[A-ZА-ЯІЇЄ][a-zа-яіїє']+(?:\\s[A-ZА-ЯІЇЄ]?[a-zа-яіїє']+)*$";
    public static final String YEAR = "^(19|20)\\d{2}$";
    public static final String COUNTRY = "^[A-Za-z ]{2,30}$\n";
    public static final String STATS = "^\\d+(\\.\\d{1,2})?$\n";
    public static final String KILOMETRAGE = "^\\d+$\n";
    public static final String NUMBER = "/\\d+";
}
