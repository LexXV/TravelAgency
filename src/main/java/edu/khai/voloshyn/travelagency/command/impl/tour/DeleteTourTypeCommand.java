package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Delete Tour Type Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class DeleteTourTypeCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(DeleteTourTypeCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getTourTypeService().
                    deleteTourTypeById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_TYPE)));
            request.getSession().setAttribute(SessionAttribute.TOUR_TYPES,
                    ServiceFactory.getInstance().getTourTypeService().getAllTourTypes());
        } catch (ServiceException e) {
            LOGGER.error(Message.DELETE_TOUR_TYPE_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
