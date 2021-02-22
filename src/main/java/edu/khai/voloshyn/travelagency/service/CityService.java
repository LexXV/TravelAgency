package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface CityService {
    void createCity(City city) throws ServiceException;

    void deleteCityById(int cityId) throws ServiceException;

    City findCityById(int cityId) throws ServiceException;

    List<City> getAllCities() throws ServiceException;
}
