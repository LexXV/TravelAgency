package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.HotelDAO;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.HotelService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.ProperNameValidator;
import edu.khai.voloshyn.travelagency.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class HotelServiceImpl implements HotelService {
    private static final Logger LOGGER = LogManager.getLogger(HotelServiceImpl.class.getName());

    private HotelDAO hotelDAO = DAOFactory.getInstance().getHotelDAO();

    private HotelServiceImpl() {
    }

    public static HotelService getInstance() {
        return HotelServiceImpl.HotelServiceImplHolder.INSTANCE;
    }

    @Override
    public void createHotel(Hotel hotel) throws ServiceException {
        Validator validator = new ProperNameValidator(hotel.getHotel());
        try {
            validator.validate();
            if (checkHotelExistence(hotel.getHotel())) {
                hotelDAO.create(hotel);
            }
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.CREATE_HOTEL_ERROR);
            throw new ServiceException(Message.CREATE_HOTEL_ERROR, e);
        }
    }

    @Override
    public void deleteHotelById(int hotelId) throws ServiceException {
        try {
            hotelDAO.delete(hotelId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_HOTEL_ERROR);
            throw new ServiceException(Message.DELETE_HOTEL_ERROR, e);
        }
    }

    @Override
    public Hotel findHotelById(int hotelId) throws ServiceException {
        try {
            return hotelDAO.findById(hotelId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_HOTEL_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_HOTEL_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<Hotel> getAllHotels() throws ServiceException {
        try {
            return hotelDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_HOTELS_ERROR);
            throw new ServiceException(Message.GET_ALL_HOTELS_ERROR, e);
        }
    }

    private boolean checkHotelExistence(String hotel) throws DAOException {
        return hotelDAO.findHotel(hotel) == null;
    }

    private static final class HotelServiceImplHolder {
        private static final HotelServiceImpl INSTANCE = new HotelServiceImpl();
    }
}
