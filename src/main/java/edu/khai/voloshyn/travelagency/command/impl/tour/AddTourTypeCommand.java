package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.TourType;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTourTypeCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(AddTourTypeCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        TourType tourType = new TourType();
        tourType.setType(request.getParameter(JspParameterType.TOUR_TYPE));
        try {
            ServiceFactory.getInstance().getTourTypeService().createTourType(tourType);
            request.getSession().setAttribute(SessionAttribute.TOUR_TYPES,
                    ServiceFactory.getInstance().getTourTypeService().getAllTourTypes());
        } catch (ServiceException e) {
            LOGGER.error(Message.ADD_TOUR_TYPE_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
