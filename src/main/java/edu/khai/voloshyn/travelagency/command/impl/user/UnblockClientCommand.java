package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.CommandType;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.CommandFactory;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UnblockClientCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(UnblockClientCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getUserService().
                    unblockClient(Integer.parseInt(request.
                            getParameter(JspParameterType.USER_ID)));
            Command getUsersList = CommandFactory.getInstance().
                    getCommand(CommandType.GET_USERS_LIST.toString());
            return getUsersList.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.UNBLOCK_CLIENT_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }
}
