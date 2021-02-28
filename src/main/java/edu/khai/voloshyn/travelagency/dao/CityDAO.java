package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.exception.DAOException;

/**
 * The interface City DAO.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface CityDAO extends DAO<City> {
    String findCity(String city) throws DAOException;
}
