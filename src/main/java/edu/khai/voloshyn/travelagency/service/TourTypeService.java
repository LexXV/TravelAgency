package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.TourType;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface TourTypeService {
    void createTourType(TourType tourType) throws ServiceException;

    void deleteTourTypeById(int tourTypeId) throws ServiceException;

    TourType findTourTypeById(int tourTypeId) throws ServiceException;

    List<TourType> getAllTourTypes() throws ServiceException;
}
