package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.TourType;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface Tour Type Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface TourTypeService {
    void createTourType(TourType tourType) throws ServiceException;

    void deleteTourTypeById(int tourTypeId) throws ServiceException;

    TourType findTourTypeById(int tourTypeId) throws ServiceException;

    List<TourType> getAllTourTypes() throws ServiceException;
}
