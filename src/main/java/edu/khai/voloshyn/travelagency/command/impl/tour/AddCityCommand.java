package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddCityCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(AddCityCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        City city = new City();
        city.setCity(request.getParameter(JspParameterType.CITY));
        try {
            ServiceFactory.getInstance().getCityService().createCity(city);
            request.getSession().setAttribute(SessionAttribute.CITIES,
                    ServiceFactory.getInstance().getCityService().getAllCities());
        } catch (ServiceException e) {
            LOGGER.error(Message.ADD_CITY_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
