package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.CommandType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.CommandFactory;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteClientCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteClientCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getUserService().
                    deleteClient(((User) request.getSession().getAttribute(SessionAttribute.USER)).getUserId());
            Command signOut = CommandFactory.getInstance().
                    getCommand(CommandType.SIGN_OUT.toString());
            return signOut.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.DELETE_CLIENT_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }
}
