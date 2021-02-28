package edu.khai.voloshyn.travelagency.command.impl.redirect;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.PositiveIntValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Redirect To Submit Buy Page Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class RedirectToSubmitBuyPageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RedirectToSubmitBuyPageCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String tourNumber = request.getParameter(JspParameterType.TOUR_NUMBER);
        request.getSession().setAttribute(SessionAttribute.TOUR_NUMBER, tourNumber);
        try {
            new PositiveIntValidator(Integer.parseInt(tourNumber)).validate();
            if (Integer.parseInt(tourNumber) <= ServiceFactory.getInstance().getTourService().
                    findTourById(Integer.parseInt((String) request.getSession().
                            getAttribute(SessionAttribute.TOUR_ID))).getPlaces()) {
                return PageType.SUBMIT_BUY_PAGE.getAddress();
            }
        } catch (ServiceException | ValidatorException e) {
            LOGGER.error(Message.REDIRECT_TO_SUBMIT_BUY_PAGE_COMMAND_ERROR, e);
        }
        return PageType.INPUT_TOUR_NUMBER_PAGE.getAddress();
    }
}
