package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.DAOException;

/**
 * The interface Tourist DAO.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface TouristDAO extends DAO<Tourist> {
    String findTourist(String tourist) throws DAOException;
}
