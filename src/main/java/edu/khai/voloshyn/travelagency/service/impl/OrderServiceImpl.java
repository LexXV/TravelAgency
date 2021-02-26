package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.OrderDAO;
import edu.khai.voloshyn.travelagency.entity.Order;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.OrderService;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = LogManager.getLogger(OrderServiceImpl.class.getName());

    private OrderDAO orderDAO = DAOFactory.getInstance().getOrderDAO();

    private OrderServiceImpl() {
    }

    public static OrderService getInstance() {
        return OrderServiceImplHolder.INSTANCE;
    }

    @Override
    public void createOrder(Order order) throws ServiceException {
        try {
            orderDAO.create(order);
        } catch (DAOException e) {
            LOGGER.error(Message.CREATE_ORDER_ERROR);
            throw new ServiceException(Message.CREATE_ORDER_ERROR, e);
        }
    }

    @Override
    public void deleteOrder(int orderId) throws ServiceException {
        try {
            orderDAO.delete(orderId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_ORDER_ERROR);
            throw new ServiceException(Message.DELETE_ORDER_ERROR, e);
        }
    }
    
    @Override
    public void setBoughtOrder(int orderId) throws ServiceException {
        try {
            orderDAO.setBought(orderId);
        } catch (DAOException e) {
            LOGGER.error(Message.SET_BOUGHT_ORDER_ERROR);
            throw new ServiceException(Message.SET_BOUGHT_ORDER_ERROR, e);
        }
    }

    @Override
    public Order findOrderById(int orderId) throws ServiceException {
        try {
            return orderDAO.findById(orderId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_ORDER_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_ORDER_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<Order> getAllOrders() throws ServiceException {
        try {
            return orderDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_ORDERS_ERROR);
            throw new ServiceException(Message.GET_ALL_ORDERS_ERROR, e);
        }
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) throws ServiceException {
        try {
            return orderDAO.findOrdersByUserId(userId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_ORDERS_BY_USER_ID_ERROR);
            throw new ServiceException(Message.FIND_ORDERS_BY_USER_ID_ERROR, e);
        }
    }

    @Override
    public void updateOrdersStatus() throws ServiceException {
        try {
            orderDAO.updateOrdersStatus();
        } catch (DAOException e) {
            LOGGER.error(Message.UPDATE_ORDERS_STATUS_ERROR);
            throw new ServiceException(Message.UPDATE_ORDERS_STATUS_ERROR, e);
        }
    }

    @Override
    public void updateUserDiscount(User user) throws ServiceException {
        try {
            orderDAO.updateUserDiscount(user);
        } catch (DAOException e) {
            LOGGER.error(Message.UPDATE_USER_DISCOUNT_ERROR);
            throw new ServiceException(Message.UPDATE_USER_DISCOUNT_ERROR, e);
        }
    }
    
    @Override
    public void updateTourDiscount(Tour tour) throws ServiceException {
        try {
            orderDAO.updateTourDiscount(tour);
        } catch (DAOException e) {
            LOGGER.error(Message.UPDATE_TOUR_DISCOUNT_ERROR);
            throw new ServiceException(Message.UPDATE_TOUR_DISCOUNT_ERROR, e);
        }
    }

    private static final class OrderServiceImplHolder {
        private static final OrderServiceImpl INSTANCE = new OrderServiceImpl();
    }
}
