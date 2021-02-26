package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.City;
import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.DAOException;

import java.util.Date;
import java.util.List;

public interface TourDAO extends DAO<Tour> {
    void unHotTour(int tourId) throws DAOException;

    void setHotTour(int tourId) throws DAOException;

    List<Tour> getHotTours() throws DAOException;

    List<Tour> searchTourByParameters(City city, Hotel hotel, Tourist tourist, Date departureDate, int days, double cost) throws DAOException;

    List<Tour> getToursByCityId(int cityId) throws DAOException;
    
    List<Tour> getToursByHotelId(int hotelId) throws DAOException;
    
    List<Tour> getToursByTouristId(int touristId) throws DAOException;

    List<Tour> getToursByTourTypeId(int tourTypeId) throws DAOException;

    void buyTour(Tour tour, int amount) throws DAOException;

    void returnTour(Tour tour, int amount) throws DAOException;

    void updateArchivedTours() throws DAOException;
}
