package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.constants.ValidatorRegex;

import java.util.regex.Pattern;

public class PhoneValidator extends Validator {

    public PhoneValidator(String phone) {
        pattern = Pattern.compile(ValidatorRegex.PHONE);
        matcher = pattern.matcher(phone);
    }

    @Override
    public void validate() throws ValidatorException {
        if (!matcher.find()) {
            throw new ValidatorException(Message.INCORRECT_PHONE);
        }
        if (hasNext()) {
            next.validate();
        }
    }
}
