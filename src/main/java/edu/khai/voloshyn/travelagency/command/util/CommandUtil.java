package edu.khai.voloshyn.travelagency.command.util;

import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.*;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class CommandUtil {
    private static final Logger LOGGER = LogManager.getLogger(CommandUtil.class.getName());

    public void initializeTourParameters(HttpServletRequest request) {
        List<Tour> tours = new ArrayList<>();
        List<City> cities = new ArrayList<>();
        List<Hotel> hotels = new ArrayList<>();
        List<Tourist> tourists = new ArrayList<>();
        List<TourType> tourTypes = new ArrayList<>();
        List<Transport> transports = new ArrayList<>();
        try {
            tours = ServiceFactory.getInstance().getTourService().getAllTours();
            cities = ServiceFactory.getInstance().getCityService().getAllCities();
            hotels = ServiceFactory.getInstance().getHotelService().getAllHotels();
            tourists = ServiceFactory.getInstance().getTouristService().getAllTourists();
            tourTypes = ServiceFactory.getInstance().getTourTypeService().getAllTourTypes();
            transports = ServiceFactory.getInstance().getTransportService().getAllTransports();
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
        request.getSession().setAttribute(SessionAttribute.TRANSPORTS, transports);
        request.getSession().setAttribute(SessionAttribute.TOURS, tours);
        request.getSession().setAttribute(SessionAttribute.CITIES, cities);
        request.getSession().setAttribute(SessionAttribute.HOTELS, hotels);
        request.getSession().setAttribute(SessionAttribute.TOURISTS, tourists);
        request.getSession().setAttribute(SessionAttribute.TOUR_TYPES, tourTypes);
    }


    public void setOrderSessionAttributes(HttpServletRequest request, User user, Tour tour) {
        request.getSession().setAttribute(SessionAttribute.TOUR_NUMBER, tour.getPlaces());
        request.getSession().setAttribute(SessionAttribute.USER, user);
    }

    public void setTourSessionAttribute(HttpServletRequest request) {
        String tourId = request.getParameter(JspParameterType.TOUR_ID);
        request.getSession().setAttribute(SessionAttribute.TOUR_ID, tourId);
        try {
            request.getSession().setAttribute(SessionAttribute.TOUR,
                    ServiceFactory.getInstance().getTourService().findTourById(Integer.parseInt(tourId)));
        } catch (ServiceException e) {
            LOGGER.error(e);
        }
    }
}
