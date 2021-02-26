package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.DAOException;

public interface TouristDAO extends DAO<Tourist> {
    String findTourist(String tourist) throws DAOException;
}
