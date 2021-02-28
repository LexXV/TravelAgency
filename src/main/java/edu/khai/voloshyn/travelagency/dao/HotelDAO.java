package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.Hotel;
import edu.khai.voloshyn.travelagency.exception.DAOException;

/**
 * The interface Hotel DAO.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface HotelDAO extends DAO<Hotel> {
    String findHotel(String hotel) throws DAOException;
}
