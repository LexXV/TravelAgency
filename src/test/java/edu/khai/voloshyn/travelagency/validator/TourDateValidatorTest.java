package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.sql.Date;

public class TourDateValidatorTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();
    private Date date;

    @Test
    public void validatorForDateBefore() throws ValidatorException {
        date = Date.valueOf("2021-01-20");
        action();
    }

    private void action() throws ValidatorException {
        exceptionRule.expect(ValidatorException.class);
        Validator validator = new TourDateValidator(date);
        validator.validate();
    }
}