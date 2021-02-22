package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PositiveIntValidatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private int number;

    @Test
    public void validatorForZero() throws ValidatorException {
        number = 0;
        action();
    }

    @Test
    public void validatorForNegative() throws ValidatorException {
        number = -1;
        action();
    }

    private void action() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        Validator validator = new PositiveIntValidator(number);
        validator.validate();
    }
}