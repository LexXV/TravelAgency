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

public class DeleteTouristCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(DeleteTouristCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getTouristService().
                    deleteTouristById(Integer.parseInt(request.getParameter(JspParameterType.TOURIST)));
            request.getSession().setAttribute(SessionAttribute.TOURISTS,
                    ServiceFactory.getInstance().getTouristService().getAllTourists());
        } catch (ServiceException e) {
            LOGGER.error(Message.DELETE_TOURIST_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
