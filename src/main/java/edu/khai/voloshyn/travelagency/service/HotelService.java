package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface Hotel Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface HotelService {
    void createHotel(Hotel hotel) throws ServiceException;

    void deleteHotelById(int hotelId) throws ServiceException;

    Hotel findHotelById(int hotelId) throws ServiceException;

    List<Hotel> getAllHotels() throws ServiceException;
}
