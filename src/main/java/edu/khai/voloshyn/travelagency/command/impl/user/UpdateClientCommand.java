package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateClientCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UpdateClientCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User client = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            initializeClientWithNewParameters(request, client);
            ServiceFactory.getInstance().getUserService().updateClient(client);
            request.getSession().setAttribute(SessionAttribute.USER, client);
            return PageType.USER_INFO_PAGE.getAddress();
        } catch (ServiceException e) {
            LOGGER.error(Message.UPDATE_CLIENT_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }

    private void initializeClientWithNewParameters(HttpServletRequest request, User client) {
        client.setName(request.getParameter(JspParameterType.NAME));
        client.setSurname(request.getParameter(JspParameterType.SURNAME));
        client.setPhone(request.getParameter(JspParameterType.PHONE));
        client.setCash(Float.parseFloat(request.getParameter(JspParameterType.CASH)));
    }
}
