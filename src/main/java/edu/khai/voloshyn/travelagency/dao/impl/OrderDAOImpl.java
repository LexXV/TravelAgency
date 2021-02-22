package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.OrderDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.*;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.pool.ConnectionPool;
import edu.khai.voloshyn.travelagency.pool.ProxyConnection;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private static final Logger LOGGER = LogManager.getLogger(OrderDAOImpl.class.getName());

    private static final int CREATE_USER_ID_INDEX = 1;
    private static final int CREATE_TOUR_ID_INDEX = 2;
    private static final int CREATE_TOUR_NUMBER_INDEX = 3;
    private static final int CREATE_PRICE_INDEX = 4;
    private static final int USER_ID_INDEX = 1;
    private static final int ORDER_ID_INDEX = 1;
    private static final int DISCOUNT_ID_INDEX = 1;
    private static final int DELETE_ORDER_ID_INDEX = 1;
    private static final int UPDATE_USER_DISCOUNT_ID_INDEX = 1;
    private static final int UPDATE_USER_ID_INDEX = 2;
    private static final double MIN_EXPENDITURE_SUM = 1000;
    private static final double MIDDLE_EXPENDITURE_SUM = 5000;
    private static final double MAX_EXPENDITURE_SUM = 10000;
    private static final int DISCOUNT_STATUS_START = 4;
    private static final int DISCOUNT_STATUS_MIN = 3;
    private static final int DISCOUNT_STATUS_MIDDLE = 2;
    private static final int DISCOUNT_STATUS_MAX = 1;

    private OrderDAOImpl() {
    }

    public static OrderDAO getInstance() {
        return OrderDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(Order order) throws DAOException {
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.CREATE_ORDER)) {
            initializeCreateOrderStatement(statement, order);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_ORDER_ERROR);
            throw new DAOException(Message.CREATE_ORDER_ERROR, e);
        }
    }

    @Override
    public void delete(int orderId) throws DAOException {
        try {
            try (ProxyConnection connection =
                         new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement =
                         connection.prepareStatement(SqlStatement.DELETE_ORDER)) {
                statement.setInt(DELETE_ORDER_ID_INDEX, orderId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_ORDER_ERROR);
            throw new DAOException(Message.DELETE_ORDER_ERROR, e);
        }
    }

    @Override
    public void updateUserDiscount(User user) throws DAOException {
        try {
            int updatedDiscountId = calculateUserDiscountId(user.getUserId());
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_USER_DISCOUNT_ID)) {
                statement.setInt(UPDATE_USER_DISCOUNT_ID_INDEX, updatedDiscountId);
                statement.setInt(UPDATE_USER_ID_INDEX, user.getUserId());
                statement.executeUpdate();
            }
            user.setDiscount(findDiscountById(updatedDiscountId));
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_USER_DISCOUNT_ERROR);
            throw new DAOException(Message.UPDATE_USER_DISCOUNT_ERROR, e);
        }
    }

    @Override
    public Order findById(int orderId) throws DAOException {
        Order order = new Order();
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.FIND_ORDER_BY_ID)) {
            statement.setInt(ORDER_ID_INDEX, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeOrder(order, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_ORDER_BY_ID_ERROR);
            throw new DAOException(Message.FIND_ORDER_BY_ID_ERROR, e);
        }
        return order;
    }

    @Override
    public List<Order> getAll() throws DAOException {
        List<Order> listToReturn = new ArrayList<>();
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.GET_ALL_ORDERS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    initializeOrder(order, resultSet);
                    listToReturn.add(order);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_ORDERS_ERROR);
            throw new DAOException(Message.GET_ALL_ORDERS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public List<Order> findOrdersByUserId(int userId) throws DAOException {
        List<Order> listToReturn = new ArrayList<>();
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.GET_ALL_ORDERS_BY_USER_ID)) {
            statement.setInt(USER_ID_INDEX, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Order order = new Order();
                    initializeOrder(order, resultSet);
                    listToReturn.add(order);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_ORDERS_BY_USER_ID_ERROR);
            throw new DAOException(Message.FIND_ORDERS_BY_USER_ID_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public void updateOrdersStatus() throws DAOException {
        List<Order> orderList = getAll();
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.SET_BOUGHT_ORDER)) {
            for (Order order : orderList) {
                if (order.getTour().getDepartureDate().
                        before(Calendar.getInstance().getTime())) {
                    statement.setInt(ORDER_ID_INDEX, order.getOrderId());
                    order.setOrderStatus(OrderStatus.BOUGHT);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_ORDERS_STATUS_ERROR);
            throw new DAOException(Message.UPDATE_ORDERS_STATUS_ERROR, e);
        }
    }

    @Override
    public void update(Order id) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(Order order) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private double getAllUserExpendituresSum(int id) throws DAOException {
        double sum = 0;
        try {
            try (ProxyConnection connection =
                         new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement =
                         connection.prepareStatement(SqlStatement.SUM_ALL_USER_ORDERS_PRICE)) {
                statement.setInt(USER_ID_INDEX, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        sum = resultSet.getDouble(SqlColumn.USER_SUM.toString());
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_USER_EXPENDITURES_SUM_ERROR);
            throw new DAOException(Message.GET_ALL_USER_EXPENDITURES_SUM_ERROR, e);
        }
        return sum;
    }

    private Discount findDiscountById(int discountId) throws DAOException {
        Discount discount = new Discount();
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.FIND_DISCOUNT_BY_ID)) {
            statement.setInt(DISCOUNT_ID_INDEX, discountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    discount.setId(resultSet.getInt(SqlColumn.USER_DISCOUNT_ID.toString()));
                    discount.setDiscountSize(resultSet.getDouble(SqlColumn.USER_DISCOUNT_SIZE.toString()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_DISCOUNT_BY_ID_ERROR);
            throw new DAOException(Message.FIND_DISCOUNT_BY_ID_ERROR, e);
        }
        return discount;
    }

    private int calculateUserDiscountId(int userId) throws DAOException {
        double userExpenditure = getAllUserExpendituresSum(userId);
        if (userExpenditure < MIN_EXPENDITURE_SUM) {
            return DISCOUNT_STATUS_START;
        } else if (userExpenditure < MIDDLE_EXPENDITURE_SUM) {
            return DISCOUNT_STATUS_MIN;
        } else if (userExpenditure < MAX_EXPENDITURE_SUM) {
            return DISCOUNT_STATUS_MIDDLE;
        } else {
            return DISCOUNT_STATUS_MAX;
        }
    }

    private void initializeCreateOrderStatement(PreparedStatement statement,
                                                Order order) throws SQLException {
        statement.setInt(CREATE_USER_ID_INDEX, order.getUser().getUserId());
        statement.setInt(CREATE_TOUR_ID_INDEX, order.getTour().getTourId());
        statement.setInt(CREATE_TOUR_NUMBER_INDEX, order.getNumber());
        statement.setDouble(CREATE_PRICE_INDEX, order.getPrice());
    }

    private void initializeOrder(Order order, ResultSet resultSet) throws SQLException {
        order.setOrderId(resultSet.getInt(SqlColumn.ORDER_ID.toString()));
        order.setUser(new User(resultSet.getInt(SqlColumn.USER_ID.toString()),
                resultSet.getString(SqlColumn.USER_LOGIN.toString())));
        order.setTour(new Tour(resultSet.getInt(SqlColumn.TOUR_ID.toString()),
                resultSet.getString(SqlColumn.TOUR_NAME.toString()),
                resultSet.getDate(SqlColumn.TOUR_DEPARTURE_DATE.toString())));
        order.setNumber(resultSet.getInt(SqlColumn.TOUR_NUMBER.toString()));
        order.setPrice(resultSet.getDouble(SqlColumn.PRICE.toString()));
        order.setOrderStatus(OrderStatus.valueOf(resultSet.
                getString(SqlColumn.ORDER_STATUS.toString()).toUpperCase()));
    }

    private static final class OrderDAOImplHolder {
        private static final OrderDAOImpl INSTANCE = new OrderDAOImpl();
    }
}
