package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface TouristService {
    void createTourist(Tourist tourist) throws ServiceException;

    void deleteTouristById(int touristId) throws ServiceException;

    Tourist findTouristById(int touristId) throws ServiceException;

    List<Tourist> getAllTourists() throws ServiceException;
}
