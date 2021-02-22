package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.TourType;
import edu.khai.voloshyn.travelagency.exception.DAOException;

public interface TourTypeDAO extends DAO<TourType> {
    String findTourType(String tourType) throws DAOException;
}
