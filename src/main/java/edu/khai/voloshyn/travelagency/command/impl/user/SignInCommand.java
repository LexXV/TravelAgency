package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.command.impl.HomeCommand;
import edu.khai.voloshyn.travelagency.entity.Role;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SignInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        try {
            User user = ServiceFactory.getInstance().getUserService().
                    signIn(request.getParameter(JspParameterType.LOGIN),
                            request.getParameter(JspParameterType.PASSWORD));
            if (user != null && !user.getRole().equals(Role.BLOCKED)) {
                session.setAttribute(SessionAttribute.USER, user);
                session.setAttribute(SessionAttribute.ROLE, user.getRole());
                return new HomeCommand().execute(request, response);
            }
        } catch (ServiceException e) {
            LOGGER.error(Message.SIGN_IN_COMMAND_ERROR, e);
        }
        return PageType.REPEAT_SIGN_IN_PAGE.getAddress();
    }
}
