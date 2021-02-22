package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.HotelDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.Hotel;
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

public class HotelDAOImpl implements HotelDAO {
    private static final Logger LOGGER = LogManager.getLogger(HotelDAOImpl.class.getName());

    private static final int HOTEL_INDEX = 1;
    private static final int HOTEL_ID_INDEX = 1;

    private HotelDAOImpl() {
    }

    public static HotelDAO getInstance() {
        return HotelDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(Hotel hotel) throws DAOException {
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.CREATE_HOTEL)) {
            statement.setString(HOTEL_INDEX, hotel.getHotel());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_HOTEL_ERROR);
            throw new DAOException(Message.CREATE_HOTEL_ERROR, e);
        }
    }

    @Override
    public void delete(int hotelId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_HOTEL)) {
                statement.setInt(HOTEL_ID_INDEX, hotelId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_HOTEL_ERROR);
            throw new DAOException(Message.DELETE_HOTEL_ERROR, e);
        }
    }

    @Override
    public Hotel findById(int hotelId) throws DAOException {
        Hotel hotel = new Hotel();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_HOTEL_BY_ID)) {
            statement.setInt(HOTEL_ID_INDEX, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeHotel(hotel, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_HOTEL_BY_ID_ERROR);
            throw new DAOException(Message.FIND_HOTEL_BY_ID_ERROR, e);
        }
        return hotel;
    }

    @Override
    public List<Hotel> getAll() throws DAOException {
        List<Hotel> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_HOTELS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    initializeHotel(hotel, resultSet);
                    listToReturn.add(hotel);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_HOTELS_ERROR);
            throw new DAOException(Message.GET_ALL_HOTELS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public String findHotel(String hotel) throws DAOException {
        String hotelToReturn = null;
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CHECK_HOTEL_EXISTENCE)) {
            statement.setString(HOTEL_INDEX, hotel);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    hotelToReturn = resultSet.getString(SqlColumn.HOTEL.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_HOTEL_ERROR);
            throw new DAOException(Message.FIND_HOTEL_ERROR, e);
        }
        return hotelToReturn;
    }

    private void initializeHotel(Hotel hotel, ResultSet resultSet) throws SQLException {
        hotel.setHotelId(resultSet.getInt(SqlColumn.HOTEL_ID.toString()));
        hotel.setHotel(resultSet.getString(SqlColumn.HOTEL.toString()));
    }

    @Override
    public void update(Hotel hotelId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(Hotel hotel) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class HotelDAOImplHolder {
        private static final HotelDAOImpl INSTANCE = new HotelDAOImpl();
    }
}
