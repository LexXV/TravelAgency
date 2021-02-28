package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.entity.Order;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.DAOException;

import java.util.List;

/**
 * The interface Order DAO.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface OrderDAO extends DAO<Order> {
    void updateUserDiscount(User user) throws DAOException;
    
    void updateTourDiscount(Tour tour) throws DAOException;

    List<Order> findOrdersByUserId(int userId) throws DAOException;

    void updateOrdersStatus() throws DAOException;
    
    void setBought(int id) throws DAOException;
}
