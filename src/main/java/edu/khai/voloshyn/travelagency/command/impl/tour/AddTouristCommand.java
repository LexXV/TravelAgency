package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddTouristCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(AddTouristCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Tourist tourist = new Tourist();
        tourist.setTourist(request.getParameter(JspParameterType.TOURIST));
        try {
            ServiceFactory.getInstance().getTouristService().createTourist(tourist);
            request.getSession().setAttribute(SessionAttribute.TOURISTS,
                    ServiceFactory.getInstance().getTouristService().getAllTourists());
        } catch (ServiceException e) {
            LOGGER.error(Message.ADD_TOURIST_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
