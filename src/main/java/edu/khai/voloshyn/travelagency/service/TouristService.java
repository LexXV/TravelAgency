package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface Tourist Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface TouristService {
    void createTourist(Tourist tourist) throws ServiceException;

    void deleteTouristById(int touristId) throws ServiceException;

    Tourist findTouristById(int touristId) throws ServiceException;

    List<Tourist> getAllTourists() throws ServiceException;
}
