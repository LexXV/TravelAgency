package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Order;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface OrderService {
    void createOrder(Order order) throws ServiceException;

    void deleteOrder(int orderId) throws ServiceException;

    Order findOrderById(int orderId) throws ServiceException;

    List<Order> getAllOrders() throws ServiceException;

    List<Order> findOrdersByUserId(int userId) throws ServiceException;

    void updateOrdersStatus() throws ServiceException;

    void updateUserDiscount(User user) throws ServiceException;
}
