package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.List;

public class SearchToursByParameters implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SearchToursByParameters.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Tour> tours;
            City city = ServiceFactory.getInstance().getCityService().
                    findCityById(Integer.parseInt(request.getParameter(JspParameterType.TO_CITY)));
            Hotel hotel = ServiceFactory.getInstance().getHotelService().
                    findHotelById(Integer.parseInt(request.getParameter(JspParameterType.TO_HOTEL)));
            Tourist tourist = ServiceFactory.getInstance().getTouristService().
                    findTouristById(Integer.parseInt(request.getParameter(JspParameterType.TO_TOURIST)));
            Date date = Date.valueOf(request.getParameter(JspParameterType.DEPARTURE_DATE));
            int days = Integer.parseInt(request.getParameter(JspParameterType.DAYS));
            double cost = Double.parseDouble(request.getParameter(JspParameterType.COST));
            tours = ServiceFactory.getInstance().getTourService().searchToursByParameters(city, hotel, tourist, date, days, cost);
            request.getSession().setAttribute(SessionAttribute.TOURS, tours);
            return PageType.TOURS_LIST_PAGE.getAddress();
        } catch (ServiceException e) {
            LOGGER.error(Message.SEARCH_TOURS_BY_PARAMETERS_COMMAND_ERROR, e);
        }
        return PageType.SEARCH_PAGE.getAddress();
    }
}
