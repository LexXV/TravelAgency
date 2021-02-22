package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.CityDAO;
import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.CityService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.ProperNameValidator;
import edu.khai.voloshyn.travelagency.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class CityServiceImpl implements CityService {
    private static final Logger LOGGER = LogManager.getLogger(CityServiceImpl.class.getName());

    private CityDAO cityDAO = DAOFactory.getInstance().getCityDAO();

    private CityServiceImpl() {
    }

    public static CityService getInstance() {
        return CityServiceImpl.CityServiceImplHolder.INSTANCE;
    }

    @Override
    public void createCity(City city) throws ServiceException {
        Validator validator = new ProperNameValidator(city.getCity());
        try {
            validator.validate();
            if (checkCityExistence(city.getCity())) {
                cityDAO.create(city);
            }
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.CREATE_CITY_ERROR);
            throw new ServiceException(Message.CREATE_CITY_ERROR, e);
        }
    }

    @Override
    public void deleteCityById(int cityId) throws ServiceException {
        try {
            cityDAO.delete(cityId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_CITY_ERROR);
            throw new ServiceException(Message.DELETE_CITY_ERROR, e);
        }
    }

    @Override
    public City findCityById(int cityId) throws ServiceException {
        try {
            return cityDAO.findById(cityId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_CITY_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_CITY_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<City> getAllCities() throws ServiceException {
        try {
            return cityDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_CITIES_ERROR);
            throw new ServiceException(Message.GET_ALL_CITIES_ERROR, e);
        }
    }

    private boolean checkCityExistence(String city) throws DAOException {
        return cityDAO.findCity(city) == null;
    }

    private static final class CityServiceImplHolder {
        private static final CityServiceImpl INSTANCE = new CityServiceImpl();
    }
}
