package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.DAOException;

public interface CityDAO extends DAO<City> {
    String findCity(String city) throws DAOException;
}
