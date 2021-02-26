package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.TourDAO;
import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.TourService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Date;
import java.util.List;

public class TourServiceImpl implements TourService {
    private static final Logger LOGGER = LogManager.getLogger(TourServiceImpl.class.getName());

    private TourDAO tourDAO = DAOFactory.getInstance().getTourDAO();

    private TourServiceImpl() {
    }

    public static TourService getInstance() {
        return TourServiceImplHolder.INSTANCE;
    }

    @Override
    public void createTour(Tour tour) throws ServiceException {
        Validator validator = createTourParametersValidatorForNewTour(tour);
        try {
            validator.validate();
            tourDAO.create(tour);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.CREATE_TOUR_ERROR);
            throw new ServiceException(Message.CREATE_TOUR_ERROR, e);
        }
    }

    @Override
    public void updateTour(Tour tour) throws ServiceException {
        Validator validator = createUpdateParametersValidator(tour);
        try {
            validator.validate();
            tourDAO.update(tour);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.UPDATE_TOUR_ERROR);
            throw new ServiceException(Message.UPDATE_TOUR_ERROR, e);
        }
    }

    @Override
    public Tour findTourById(int tourId) throws ServiceException {
        try {
            return tourDAO.findById(tourId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_TOUR_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_TOUR_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<Tour> searchToursByParameters(City city, Hotel hotel, Tourist tourist, Date departureDate, int days, double cost) throws ServiceException {
        Validator validator = createSearchParametersValidator(departureDate, days, cost);
        try {
            validator.validate();
            return tourDAO.searchTourByParameters(city, hotel, tourist, departureDate, days, cost);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.SEARCH_TOUR_BY_PARAMETERS_ERROR);
            throw new ServiceException(Message.SEARCH_TOUR_BY_PARAMETERS_ERROR, e);
        }
    }

    @Override
    public List<Tour> getAllTours() throws ServiceException {
        try {
            return tourDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_TOURS_ERROR);
            throw new ServiceException(Message.GET_ALL_TOURS_ERROR, e);
        }
    }

    @Override
    public List<Tour> getHotTours() throws ServiceException {
        try {
            return tourDAO.getHotTours();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_HOT_TOURS_ERROR);
            throw new ServiceException(Message.GET_HOT_TOURS_ERROR, e);
        }
    }

    @Override
    public List<Tour> getToursByCityId(int cityId) throws ServiceException {
        try {
            return tourDAO.getToursByCityId(cityId);
        } catch (DAOException e) {
            LOGGER.error(Message.GET_TOURS_BY_CITY_ID_ERROR);
            throw new ServiceException(Message.GET_TOURS_BY_CITY_ID_ERROR, e);
        }
    }
    
    @Override
    public List<Tour> getToursByHotelId(int hotelId) throws ServiceException {
        try {
            return tourDAO.getToursByHotelId(hotelId);
        } catch (DAOException e) {
            LOGGER.error(Message.GET_TOURS_BY_HOTEL_ID_ERROR);
            throw new ServiceException(Message.GET_TOURS_BY_HOTEL_ID_ERROR, e);
        }
    }
    
    @Override
    public List<Tour> getToursByTouristId(int touristId) throws ServiceException {
        try {
            return tourDAO.getToursByTouristId(touristId);
        } catch (DAOException e) {
            LOGGER.error(Message.GET_TOURS_BY_TOURIST_ID_ERROR);
            throw new ServiceException(Message.GET_TOURS_BY_TOURIST_ID_ERROR, e);
        }
    }

    @Override
    public List<Tour> getToursByTourTypeId(int tourTypeId) throws ServiceException {
        try {
            return tourDAO.getToursByTourTypeId(tourTypeId);
        } catch (DAOException e) {
            LOGGER.error(Message.GET_TOURS_BY_TOUR_TYPE_ID_ERROR);
            throw new ServiceException(Message.GET_TOURS_BY_TOUR_TYPE_ID_ERROR, e);
        }
    }

    @Override
    public void unHotTour(int tourId) throws ServiceException {
        try {
            tourDAO.unHotTour(tourId);
        } catch (DAOException e) {
            LOGGER.error(Message.UN_HOT_TOUR_ERROR);
            throw new ServiceException(Message.UN_HOT_TOUR_ERROR, e);
        }
    }

    @Override
    public void deleteTour(int tourId) throws ServiceException {
        try {
            tourDAO.delete(tourId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_TOUR_ERROR);
            throw new ServiceException(Message.DELETE_TOUR_ERROR, e);
        }
    }

    @Override
    public void setHotTour(int tourId) throws ServiceException {
        try {
            tourDAO.setHotTour(tourId);
        } catch (DAOException e) {
            LOGGER.error(Message.SET_HOT_TOUR_ERROR);
            throw new ServiceException(Message.SET_HOT_TOUR_ERROR, e);
        }
    }

    @Override
    public void buyTour(Tour tour, int amount) throws ServiceException {
        try {
            tourDAO.buyTour(tour, amount);
        } catch (DAOException e) {
            LOGGER.error(Message.BUY_TOUR_ERROR);
            throw new ServiceException(Message.BUY_TOUR_ERROR, e);
        }
    }

    @Override
    public void returnTour(Tour tour, int amount) throws ServiceException {
        try {
            tourDAO.returnTour(tour, amount);
        } catch (DAOException e) {
            LOGGER.error(Message.RETURN_TOUR_ERROR);
            throw new ServiceException(Message.RETURN_TOUR_ERROR, e);
        }
    }

    @Override
    public void updateArchivedTours() throws ServiceException {
        try {
            tourDAO.updateArchivedTours();
        } catch (DAOException e) {
            LOGGER.error(Message.UPDATE_ARCHIVED_TOURS_ERROR);
            throw new ServiceException(Message.UPDATE_ARCHIVED_TOURS_ERROR, e);
        }
    }

    private Validator createTourParametersValidatorForNewTour(Tour tour) {
        Validator nameValidator = new ProperNameValidator(tour.getName());
        Validator costValidator = new MoneyValidator(tour.getCost());
        Validator daysValidator = new PositiveIntValidator(tour.getDays());
        Validator placesValidator = new PositiveIntValidator(tour.getPlaces());
        Validator dateValidator = new TourDateValidator(tour.getDepartureDate());
        nameValidator.setNext(costValidator);
        costValidator.setNext(daysValidator);
        daysValidator.setNext(placesValidator);
        placesValidator.setNext(dateValidator);
        return nameValidator;
    }

    private Validator createUpdateParametersValidator(Tour tour) {
        Validator costValidator = new MoneyValidator(tour.getCost());
        Validator daysValidator = new PositiveIntValidator(tour.getDays());
        Validator placesValidator = new PositiveIntValidator(tour.getPlaces());
        Validator dateValidator = new TourDateValidator(tour.getDepartureDate());
        costValidator.setNext(daysValidator);
        daysValidator.setNext(placesValidator);
        placesValidator.setNext(dateValidator);
        return costValidator;
    }

    private Validator createSearchParametersValidator(Date departureDate, int days, double cost) {
        Validator costValidator = new MoneyValidator(cost);
        Validator daysValidator = new PositiveIntValidator(days);
        Validator dateValidator = new TourDateValidator(departureDate);
        costValidator.setNext(daysValidator);
        daysValidator.setNext(dateValidator);
        return costValidator;
    }

    private static final class TourServiceImplHolder {
        private static final TourServiceImpl INSTANCE = new TourServiceImpl();
    }
}
