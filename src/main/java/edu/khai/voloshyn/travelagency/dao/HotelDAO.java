package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.exception.DAOException;

public interface HotelDAO extends DAO<Hotel> {
    String findHotel(String hotel) throws DAOException;
}
