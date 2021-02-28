package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.TourDiscount;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface Tour Discount Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface TourDiscountService {
    TourDiscount findTourDiscountById(int tourDiscountId) throws ServiceException;

    List<TourDiscount> getAllTourDiscounts() throws ServiceException;
}
