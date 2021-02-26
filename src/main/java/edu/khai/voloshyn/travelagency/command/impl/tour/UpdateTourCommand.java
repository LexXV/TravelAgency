package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.CommandType;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.CommandFactory;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

public class UpdateTourCommand implements Command {

    private static final Logger LOGGER = LogManager.getLogger(UpdateTourCommand.class.getName());

    private static void initializeTour(HttpServletRequest request, Tour tour) throws ServiceException {
        tour.setCost(Double.parseDouble(request.getParameter(JspParameterType.COST)));
        tour.setDays(Integer.parseInt(request.getParameter(JspParameterType.DAYS)));
        tour.setPlaces(Integer.parseInt(request.getParameter(JspParameterType.PLACES)));
        tour.setDepartureDate(Date.valueOf(request.getParameter(JspParameterType.DEPARTURE_DATE)));
        tour.setTransport(ServiceFactory.getInstance().getTransportService().
                findTransportById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_TRANSPORT))));
        tour.setCity(ServiceFactory.getInstance().getCityService().
                findCityById(Integer.parseInt(request.getParameter(JspParameterType.TO_CITY))));
        tour.setHotel(ServiceFactory.getInstance().getHotelService().
                findHotelById(Integer.parseInt(request.getParameter(JspParameterType.TO_HOTEL))));
        tour.setTourist(ServiceFactory.getInstance().getTouristService().
                findTouristById(Integer.parseInt(request.getParameter(JspParameterType.TO_TOURIST))));
        tour.setDepartureCity(ServiceFactory.getInstance().getCityService().
                findCityById(Integer.parseInt(request.getParameter(JspParameterType.DEPARTURE_CITY))));
        tour.setTourType(ServiceFactory.getInstance().getTourTypeService().
                findTourTypeById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_TYPE))));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String id = (String) request.getSession().getAttribute(SessionAttribute.TOUR_ID);
        try {
            Tour tour = ServiceFactory.getInstance().getTourService().findTourById(Integer.parseInt(id));
            tour.setTourId(Integer.parseInt(id));
            initializeTour(request, tour);
            ServiceFactory.getInstance().getTourService().updateTour(tour);
            Command getToursList = CommandFactory.getInstance().getCommand(CommandType.GET_TOURS_LIST.toString());
            return getToursList.execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.UPDATE_TOUR_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }
}
