package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetToursByType implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetToursByType.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Tour> tours = new ArrayList<>();
        try {
            tours = ServiceFactory.getInstance().getTourService().
                    getToursByTourTypeId(Integer.parseInt(request.
                            getParameter(JspParameterType.TOUR_TYPE_ID)));
        } catch (ServiceException e) {
            LOGGER.error(Message.GET_TOURS_BY_TYPE_COMMAND_ERROR, e);
        }
        request.getSession().setAttribute(SessionAttribute.TOURS, tours);
        return PageType.TOURS_LIST_PAGE.getAddress();
    }
}
