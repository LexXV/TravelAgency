package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.util.Message;

public class PositiveIntValidator extends Validator {
    private int number;

    public PositiveIntValidator(int number) {
        this.number = number;
    }

    @Override
    public void validate() throws ValidatorException {
        if (number <= 0) {
            throw new ValidatorException(Message.NOT_POSITIVE_NUMBER);
        }
        if (hasNext()) {
            next.validate();
        }
    }
}
