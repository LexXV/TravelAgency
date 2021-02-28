package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface City Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface CityService {
    void createCity(City city) throws ServiceException;

    void deleteCityById(int cityId) throws ServiceException;

    City findCityById(int cityId) throws ServiceException;

    List<City> getAllCities() throws ServiceException;
}
