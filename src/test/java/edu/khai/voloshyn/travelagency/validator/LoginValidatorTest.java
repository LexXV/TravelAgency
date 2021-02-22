package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class LoginValidatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private String login;

    @Test
    public void validatorForEmptyString() throws ValidatorException {
        login = "";
        action();
    }

    @Test
    public void validatorForNull() throws ValidatorException {
        login = null;
        action();
    }

    @Test
    public void validatorForSymbols() throws ValidatorException {
        login = "...,!.";
        action();
    }

    @Test
    public void validatorForRussianSymbols() throws ValidatorException {
        login = "привет";
        action();
    }

    private void action() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        Validator validator = new LoginValidator(login);
        validator.validate();
    }
}