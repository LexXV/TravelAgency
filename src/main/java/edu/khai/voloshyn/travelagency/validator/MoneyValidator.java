package edu.khai.voloshyn.travelagency.validator;

import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.util.Message;

public class MoneyValidator extends Validator {
    private double money;

    public MoneyValidator(double money) {
        this.money = money;
    }

    @Override
    public void validate() throws ValidatorException {
        if (money < 0) {
            throw new ValidatorException(Message.NEGATIVE_MONEY);
        }
        if (hasNext()) {
            next.validate();
        }
    }
}
