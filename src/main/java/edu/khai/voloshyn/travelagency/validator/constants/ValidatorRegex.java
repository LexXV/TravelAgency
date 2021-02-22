package edu.khai.voloshyn.travelagency.validator.constants;

public class ValidatorRegex {
    public static final String PASSWORD = "^(?=.*[0-9]+.*)(?=.*[a-zA-Z]+.*)[0-9a-zA-Z]{6,}$";
    public static final String PROPER_NAME = "^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$";
    public static final String PHONE = "[0-9*#+() -]*";
    public static final String LOGIN = "[A-Za-z0-9_]+";

    private ValidatorRegex() {
    }
}
