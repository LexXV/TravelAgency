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

public class UpdateManagerCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UpdateManagerCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User manager = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            initializeManagerWithNewParameters(request, manager);
            ServiceFactory.getInstance().getUserService().updateManager(manager);
            request.getSession().setAttribute(SessionAttribute.USER, manager);
            return PageType.USER_INFO_PAGE.getAddress();
        } catch (ServiceException e) {
            LOGGER.error(Message.UPDATE_MANAGER_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }

    private void initializeManagerWithNewParameters(HttpServletRequest request, User manager) {
        manager.setName(request.getParameter(JspParameterType.NAME));
        manager.setSurname(request.getParameter(JspParameterType.SURNAME));
        manager.setPhone(request.getParameter(JspParameterType.PHONE));
    }
}
