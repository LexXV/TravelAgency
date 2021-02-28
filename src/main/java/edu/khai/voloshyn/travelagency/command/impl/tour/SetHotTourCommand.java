package edu.khai.voloshyn.travelagency.command.impl.tour;

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

/**
 * The type Set Hot Tour Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class SetHotTourCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SetHotTourCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            ServiceFactory.getInstance().getTourService().
                    setHotTour(Integer.parseInt(request.
                            getParameter(JspParameterType.TOUR_ID)));
            Command getToursList = CommandFactory.getInstance().
                    getCommand(CommandType.GET_TOURS_LIST.toString());
            return getToursList.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.SET_HOT_TOUR_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }
}
