package edu.khai.voloshyn.travelagency.command.impl.tour;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddHotelCommand implements Command {
    public static final Logger LOGGER = LogManager.getLogger(AddHotelCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Hotel hotel = new Hotel();
        hotel.setHotel(request.getParameter(JspParameterType.HOTEL));
        try {
            ServiceFactory.getInstance().getHotelService().createHotel(hotel);
            request.getSession().setAttribute(SessionAttribute.HOTELS,
                    ServiceFactory.getInstance().getHotelService().getAllHotels());
        } catch (ServiceException e) {
            LOGGER.error(Message.ADD_HOTEL_COMMAND_ERROR, e);
        }
        return PageType.SERVICE_PAGE.getAddress();
    }
}
