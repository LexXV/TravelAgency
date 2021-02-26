package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.TourDiscount;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface TourDiscountService {
    TourDiscount findTourDiscountById(int tourDiscountId) throws ServiceException;

    List<TourDiscount> getAllTourDiscounts() throws ServiceException;
}
