package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.TourDiscountDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.TourDiscount;
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
import java.util.List;

public class TourDiscountDAOImpl implements TourDiscountDAO {
    private static final Logger LOGGER = LogManager.getLogger(TourDiscountDAOImpl.class.getName());

    private static final int TOUR_DISCOUNT_ID_INDEX = 1;

    private TourDiscountDAOImpl() {
    }

    public static TourDiscountDAO getInstance() {
        return TourDiscountDAOImplHolder.INSTANCE;
    }

    @Override
    public TourDiscount findById(int tourDiscountId) throws DAOException {
        TourDiscount tourDiscount = new TourDiscount();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TOUR_DISCOUNT_BY_ID)) {
            statement.setInt(TOUR_DISCOUNT_ID_INDEX, tourDiscountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeTourDiscount(tourDiscount, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOUR_DISCOUNT_BY_ID_ERROR);
            throw new DAOException(Message.FIND_TOUR_DISCOUNT_BY_ID_ERROR, e);
        }
        return tourDiscount;
    }

    @Override
    public List<TourDiscount> getAll() throws DAOException {
        List<TourDiscount> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOUR_DISCOUNT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TourDiscount tourDiscount = new TourDiscount();
                    initializeTourDiscount(tourDiscount, resultSet);
                    listToReturn.add(tourDiscount);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_TOUR_DISCOUNTS_ERROR);
            throw new DAOException(Message.GET_ALL_TOUR_DISCOUNTS_ERROR, e);
        }
        return listToReturn;
    }

    private void initializeTourDiscount(TourDiscount tourDiscount, ResultSet resultSet) throws SQLException {
        tourDiscount.setId(resultSet.getInt(SqlColumn.TOUR_DISCOUNT_ID.toString()));
        tourDiscount.setDiscountSize(resultSet.getDouble(SqlColumn.TOUR_DISCOUNT_SIZE.toString()));
    }

    @Override
    public void update(TourDiscount tourDiscountId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void create(TourDiscount tourDiscount) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(TourDiscount tourDiscount) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(int tourDiscountId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class TourDiscountDAOImplHolder {
        private static final TourDiscountDAOImpl INSTANCE = new TourDiscountDAOImpl();
    }
}
