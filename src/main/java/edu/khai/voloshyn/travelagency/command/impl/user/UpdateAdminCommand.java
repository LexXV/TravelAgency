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

public class UpdateAdminCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UpdateAdminCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User admin = (User) request.getSession().getAttribute(SessionAttribute.USER);
        try {
            initializeAdminWithNewParameters(request, admin);
            ServiceFactory.getInstance().getUserService().updateAdmin(admin);
            request.getSession().setAttribute(SessionAttribute.USER, admin);
            return PageType.USER_INFO_PAGE.getAddress();
        } catch (ServiceException e) {
            LOGGER.error(Message.UPDATE_ADMIN_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }

    private void initializeAdminWithNewParameters(HttpServletRequest request, User admin) {
        admin.setName(request.getParameter(JspParameterType.NAME));
        admin.setSurname(request.getParameter(JspParameterType.SURNAME));
        admin.setPhone(request.getParameter(JspParameterType.PHONE));
    }
}
