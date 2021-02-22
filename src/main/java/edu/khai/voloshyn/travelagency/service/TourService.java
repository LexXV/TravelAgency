package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.sql.Date;
import java.util.List;

public interface TourService {
    void createTour(Tour tour) throws ServiceException;

    List<Tour> searchToursByParameters(City city, Hotel hotel, Date departureDate, int days, double cost) throws ServiceException;

    List<Tour> getAllTours() throws ServiceException;

    List<Tour> getHotTours() throws ServiceException;

    List<Tour> getToursByCityId(int cityId) throws ServiceException;
    
    List<Tour> getToursByHotelId(int hotelId) throws ServiceException;

    List<Tour> getToursByTourTypeId(int tourTypeId) throws ServiceException;

    void unHotTour(int tourId) throws ServiceException;

    void deleteTour(int tourId) throws ServiceException;

    void setHotTour(int tourId) throws ServiceException;

    void updateTour(Tour tour) throws ServiceException;

    Tour findTourById(int tourId) throws ServiceException;

    void buyTour(Tour tour, int amount) throws ServiceException;

    void returnTour(Tour tour, int amount) throws ServiceException;

    void updateArchivedTours() throws ServiceException;
}
