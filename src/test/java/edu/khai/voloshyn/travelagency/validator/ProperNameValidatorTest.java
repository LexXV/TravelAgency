package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ProperNameValidatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private String properName;

    @Test
    public void validatorForEmptyString() throws ValidatorException {
        properName = "";
        action();
    }

    @Test
    public void validatorForNull() throws ValidatorException {
        properName = null;
        action();
    }

    @Test
    public void validatorForSymbols() throws ValidatorException {
        properName = "...,!.";
        action();
    }

    @Test
    public void validatorForRussianSymbols() throws ValidatorException {
        properName = "helloприветN";
        action();
    }


    @Test
    public void validatorForStringWithoutLetters() throws ValidatorException {
        properName = "135156245";
        action();
    }

    @Test
    public void validatorForStringWithNumbers() throws ValidatorException {
        properName = "hell1";
        action();
    }

    private void action() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        Validator validator = new ProperNameValidator(properName);
        validator.validate();
    }
}