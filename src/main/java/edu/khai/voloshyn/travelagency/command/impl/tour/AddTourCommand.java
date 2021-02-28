package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

/**
 * The type Add Tour Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class AddTourCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddTourCommand.class.getName());

    private static void initializeTour(HttpServletRequest request, Tour tour) throws ServiceException {
        tour.setName(request.getParameter(JspParameterType.TOUR_NAME));
        tour.setCost(Double.parseDouble(request.getParameter(JspParameterType.COST)));
        tour.setDays(Integer.parseInt(request.getParameter(JspParameterType.DAYS)));
        tour.setPlaces(Integer.parseInt(request.getParameter(JspParameterType.PLACES)));
        tour.setDepartureDate(Date.valueOf(request.getParameter(JspParameterType.DEPARTURE_DATE)));
        tour.setTransport(ServiceFactory.getInstance().getTransportService().
                findTransportById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_TRANSPORT))));
        tour.setCity(ServiceFactory.getInstance().getCityService().
                findCityById(Integer.parseInt(request.getParameter(JspParameterType.TO_CITY))));
        tour.setDepartureCity(ServiceFactory.getInstance().getCityService().
                findCityById(Integer.parseInt(request.getParameter(JspParameterType.DEPARTURE_CITY))));
        tour.setHotel(ServiceFactory.getInstance().getHotelService().
                findHotelById(Integer.parseInt(request.getParameter(JspParameterType.TO_HOTEL))));
        tour.setTourist(ServiceFactory.getInstance().getTouristService().
                findTouristById(Integer.parseInt(request.getParameter(JspParameterType.TO_TOURIST))));
        tour.setTourType(ServiceFactory.getInstance().getTourTypeService().
                findTourTypeById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_TYPE))));
        tour.setDiscount(ServiceFactory.getInstance().getTourDiscountService().
                findTourDiscountById(Integer.parseInt(request.getParameter(JspParameterType.TOUR_DISCOUNT_SIZE))));
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Tour tour = new Tour();
            initializeTour(request, tour);
            ServiceFactory.getInstance().getTourService().createTour(tour);
            request.getSession().setAttribute(SessionAttribute.TOURS,
                    ServiceFactory.getInstance().getTourService().getAllTours());
        } catch (ServiceException e) {
            LOGGER.error(e);
            return PageType.ADD_TOUR_PAGE.getAddress();
        }
        return PageType.TOURS_LIST_PAGE.getAddress();
    }
}
