package edu.khai.voloshyn.travelagency.validator;


import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.constants.ValidatorRegex;
import com.mysql.cj.util.StringUtils;

import java.util.regex.Pattern;

public class LoginValidator extends Validator {
    private String login;

    public LoginValidator(String login) {
        this.login = login;
    }

    @Override
    public void validate() throws ValidatorException {
        try {
            pattern = Pattern.compile(ValidatorRegex.LOGIN);
            matcher = pattern.matcher(login);
        } catch (NullPointerException e) {
            throw new ValidatorException(Message.NULL_LOGIN, e);
        }
        if (StringUtils.isNullOrEmpty(login) || !matcher.find()) {
            throw new ValidatorException(Message.INCORRECT_LOGIN);
        }
        if (hasNext()) {
            next.validate();
        }
    }
}
