package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.TourDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.*;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.pool.ConnectionPool;
import edu.khai.voloshyn.travelagency.pool.ProxyConnection;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class TourDAOImpl implements TourDAO {
    private static final Logger LOGGER = LogManager.getLogger(TourDAOImpl.class.getName());

    private static final int CREATE_TOUR_NAME_INDEX = 1;
    private static final int CREATE_TOUR_COST_INDEX = 2;
    private static final int CREATE_TOUR_DEPARTURE_DATE_INDEX = 3;
    private static final int CREATE_TOUR_DAYS_INDEX = 4;
    private static final int CREATE_TOUR_PLACES_INDEX = 5;
    private static final int CREATE_TOUR_TYPE_INDEX = 6;
    private static final int CREATE_TOUR_CITY_INDEX = 7;
    private static final int CREATE_TOUR_DEPARTURE_CITY_INDEX = 8;
    private static final int CREATE_TOUR_HOTEL_INDEX = 9;
    private static final int CREATE_TOUR_TOURIST_INDEX = 10;
    private static final int CREATE_TOUR_TRANSPORT_INDEX = 11;
    private static final int CREATE_TOUR_DISCOUNT_INDEX = 12;
    private static final int CITY_ID_QUERY_INDEX = 1;
    private static final int HOTEL_ID_QUERY_INDEX = 1;
    private static final int TOURIST_ID_QUERY_INDEX = 1;
    private static final int TOUR_ID_INDEX = 1;
    private static final int TOUR_ID_UPDATE_HOT_INDEX = 1;
    private static final int UPDATE_TOUR_COST_INDEX = 1;
    private static final int UPDATE_DEPARTURE_DATE_INDEX = 2;
    private static final int UPDATE_TOUR_DAYS_INDEX = 3;
    private static final int UPDATE_TOUR_PLACES_INDEX = 4;
    private static final int UPDATE_TOUR_TYPE_INDEX = 5;
    private static final int UPDATE_TOUR_CITY_INDEX = 6;
    private static final int UPDATE_TOUR_DEPARTURE_CITY_INDEX = 7;
    private static final int UPDATE_TOUR_HOTEL_INDEX = 8;
    private static final int UPDATE_TOUR_TOURIST_INDEX = 9;
    private static final int UPDATE_TOUR_TRANSPORT_INDEX = 10;
    private static final int UPDATE_TOUR_DISCOUNT_INDEX = 11;
    private static final int UPDATE_TOUR_ID_INDEX = 12;
    private static final int UPDATE_ID_INDEX = 2;
    private static final int UPDATE_PLACES_INDEX = 1;

    private TourDAOImpl() {
    }

    public static TourDAO getInstance() {
        return TourDAOImplHolder.INSTANCE;
    }

    @Override
    public void delete(int tourId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_TOUR)) {
                statement.setInt(TOUR_ID_INDEX, tourId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_TOUR_ERROR);
            throw new DAOException(Message.DELETE_TOUR_ERROR, e);
        }
    }

    @Override
    public void update(Tour tour) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_TOUR_INFO)) {
                initializeUpdateTourStatement(statement, tour);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_TOUR_ERROR);
            throw new DAOException(Message.UPDATE_TOUR_ERROR, e);
        }
    }

    @Override
    public void unHotTour(int tourId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UN_HOT_TOUR)) {
                statement.setInt(TOUR_ID_UPDATE_HOT_INDEX, tourId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UN_HOT_TOUR_ERROR);
            throw new DAOException(Message.UN_HOT_TOUR_ERROR, e);
        }
    }

    @Override
    public void setHotTour(int tourId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.SET_HOT_TOUR)) {
                statement.setInt(TOUR_ID_UPDATE_HOT_INDEX, tourId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.SET_HOT_TOUR_ERROR);
            throw new DAOException(Message.SET_HOT_TOUR_ERROR, e);
        }
    }

    @Override
    public Tour findById(int tourId) throws DAOException {
        Tour tour = new Tour();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TOUR_BY_ID)) {
            statement.setInt(CITY_ID_QUERY_INDEX, tourId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeTour(tour, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOUR_BY_ID_ERROR);
            throw new DAOException(Message.FIND_TOUR_BY_ID_ERROR, e);
        }
        return tour;
    }

    @Override
    public List<Tour> getAll() throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                listToReturn.add(tour);
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_TOURS_ERROR);
            throw new DAOException(Message.GET_ALL_TOURS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public void create(Tour tour) throws DAOException {
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CREATE_TOUR)) {
            initializeStatementToCreateTour(statement, tour);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_TOUR_ERROR);
            throw new DAOException(Message.CREATE_TOUR_ERROR, e);
        }
    }

    @Override
    public List<Tour> getHotTours() throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if (tour.getTourStatus().getId() == 2) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_HOT_TOURS_ERROR);
            throw new DAOException(Message.GET_HOT_TOURS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public List<Tour> searchTourByParameters(City city, Hotel hotel, Tourist tourist, Date departureDate, int days, double cost) throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if ((tour.getCity().getCityId() == city.getCityId()) && (tour.getHotel().getHotelId() == hotel.getHotelId()) 
                		&& (tour.getTourist().getTouristId() == tourist.getTouristId()) && (departureDate.after(tour.getDepartureDate()))
                        && (days >= tour.getDays()) && (cost >= tour.getCost())) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.SEARCH_TOUR_BY_PARAMETERS_ERROR);
            throw new DAOException(Message.SEARCH_TOUR_BY_PARAMETERS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public List<Tour> getToursByCityId(int cityId) throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if (tour.getCity().getCityId() == cityId) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_TOURS_BY_CITY_ID_ERROR);
            throw new DAOException(Message.GET_TOURS_BY_CITY_ID_ERROR, e);
        }
        return listToReturn;
    }
    
    @Override
    public List<Tour> getToursByHotelId(int hotelId) throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if (tour.getHotel().getHotelId() == hotelId) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_TOURS_BY_HOTEL_ID_ERROR);
            throw new DAOException(Message.GET_TOURS_BY_HOTEL_ID_ERROR, e);
        }
        return listToReturn;
    }
    
    @Override
    public List<Tour> getToursByTouristId(int touristId) throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if (tour.getTourist().getTouristId() == touristId) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_TOURS_BY_TOURIST_ID_ERROR);
            throw new DAOException(Message.GET_TOURS_BY_TOURIST_ID_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public List<Tour> getToursByTourTypeId(int tourTypeId) throws DAOException {
        List<Tour> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tour tour = new Tour();
                initializeTour(tour, resultSet);
                if (tour.getTourType().getTourTypeId() == tourTypeId) {
                    listToReturn.add(tour);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_TOURS_BY_TOUR_TYPE_ID_ERROR);
            throw new DAOException(Message.GET_TOURS_BY_TOUR_TYPE_ID_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public void buyTour(Tour tour, int amount) throws DAOException {
        try {
            int updatedPlaces = tour.getPlaces() - amount;
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_TOUR_PLACES)) {
                statement.setInt(UPDATE_PLACES_INDEX, updatedPlaces);
                statement.setInt(UPDATE_ID_INDEX, tour.getTourId());
                statement.executeUpdate();
            }
            tour.setPlaces(updatedPlaces);
        } catch (SQLException e) {
            LOGGER.error(Message.BUY_TOUR_ERROR);
            throw new DAOException(Message.BUY_TOUR_ERROR, e);
        }
    }

    @Override
    public void returnTour(Tour tour, int amount) throws DAOException {
        try {
            int updatedPlaces = tour.getPlaces() + amount;
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_TOUR_PLACES)) {
                statement.setInt(UPDATE_PLACES_INDEX, updatedPlaces);
                statement.setInt(UPDATE_ID_INDEX, tour.getTourId());
                statement.executeUpdate();
            }
            tour.setPlaces(updatedPlaces);
        } catch (SQLException e) {
            LOGGER.error(Message.RETURN_TOUR_ERROR);
            throw new DAOException(Message.RETURN_TOUR_ERROR, e);
        }
    }

    @Override
    public void updateArchivedTours() throws DAOException {
        List<Tour> tours = getAll();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.SET_ARCHIVE_TOUR)) {
            for (Tour tour : tours) {
                if (tour.getDepartureDate().before(Calendar.getInstance().getTime())) {
                    statement.setInt(TOUR_ID_INDEX, tour.getTourId());
                    tour.setTourStatus(TourStatus.ARCHIVAL);
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_ARCHIVED_TOURS_ERROR);
            throw new DAOException(Message.UPDATE_ARCHIVED_TOURS_ERROR, e);
        }
    }

    @Override
    public void delete(Tour item) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private City getCityById(int cityId) throws DAOException {
        City city = new City();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_CITY_BY_ID)) {
            statement.setInt(CITY_ID_QUERY_INDEX, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    city.setCityId(resultSet.getInt(SqlColumn.CITY_ID.toString()));
                    city.setCity(resultSet.getString(SqlColumn.CITY.toString()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
        return city;
    }
    
    private Hotel getHotelById(int hotelId) throws DAOException {
        Hotel hotel = new Hotel();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_HOTEL_BY_ID)) {
            statement.setInt(HOTEL_ID_QUERY_INDEX, hotelId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    hotel.setHotelId(resultSet.getInt(SqlColumn.HOTEL_ID.toString()));
                    hotel.setHotel(resultSet.getString(SqlColumn.HOTEL.toString()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
        return hotel;
    }
    
    private Tourist getTouristById(int touristId) throws DAOException {
        Tourist tourist = new Tourist();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TOURIST_BY_ID)) {
            statement.setInt(TOURIST_ID_QUERY_INDEX, touristId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    tourist.setTouristId(resultSet.getInt(SqlColumn.TOURIST_ID.toString()));
                    tourist.setTourist(resultSet.getString(SqlColumn.TOURIST.toString()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DAOException(e);
        }
        return tourist;
    }
    
    private void initializeUpdateTourStatement(PreparedStatement statement, Tour tour) throws SQLException {
        statement.setDouble(UPDATE_TOUR_COST_INDEX, tour.getCost());
        statement.setDate(UPDATE_DEPARTURE_DATE_INDEX, tour.getDepartureDate());
        statement.setInt(UPDATE_TOUR_DAYS_INDEX, tour.getDays());
        statement.setInt(UPDATE_TOUR_PLACES_INDEX, tour.getPlaces());
        statement.setInt(UPDATE_TOUR_TYPE_INDEX, tour.getTourType().getTourTypeId());
        statement.setInt(UPDATE_TOUR_CITY_INDEX, tour.getCity().getCityId());
        statement.setInt(UPDATE_TOUR_DEPARTURE_CITY_INDEX, tour.getDepartureCity().getCityId());
        statement.setInt(UPDATE_TOUR_HOTEL_INDEX, tour.getHotel().getHotelId());
        statement.setInt(UPDATE_TOUR_TOURIST_INDEX, tour.getTourist().getTouristId());
        statement.setInt(UPDATE_TOUR_TRANSPORT_INDEX, tour.getTransport().getTransportId());
        statement.setInt(UPDATE_TOUR_DISCOUNT_INDEX, tour.getDiscount().getId());
        statement.setInt(UPDATE_TOUR_ID_INDEX, tour.getTourId());
    }

    private void initializeTour(Tour tour, ResultSet resultSet) throws SQLException, DAOException {
        tour.setTourId(resultSet.getInt(SqlColumn.TOUR_ID.toString()));
        tour.setName(resultSet.getString(SqlColumn.TOUR_NAME.toString()));
        tour.setCost(resultSet.getDouble(SqlColumn.TOUR_COST.toString()));
        tour.setDepartureDate(resultSet.getDate(SqlColumn.TOUR_DEPARTURE_DATE.toString()));
        tour.setDays(resultSet.getInt(SqlColumn.TOUR_DAYS.toString()));
        tour.setPlaces(resultSet.getInt(SqlColumn.TOUR_PLACES.toString()));
        tour.setCity(getCityById(resultSet.getInt(SqlColumn.TOUR_CITY_ID.toString())));
        tour.setDepartureCity(getCityById(resultSet.getInt(SqlColumn.TOUR_DEPARTURE_CITY_ID.toString())));
        tour.setHotel(getHotelById(resultSet.getInt(SqlColumn.TOUR_HOTEL_ID.toString())));
        tour.setTourist(getTouristById(resultSet.getInt(SqlColumn.TOUR_TOURIST_ID.toString())));
        tour.setTourStatus(TourStatus.valueOf(resultSet.getString(SqlColumn.TOUR_STATUS.toString()).toUpperCase()));
        tour.setTourType(new TourType(resultSet.getInt(SqlColumn.TOUR_TYPE_ID.toString()),
                resultSet.getString(SqlColumn.TOUR_TYPE.toString())));
        tour.setTransport(new Transport(resultSet.getInt(SqlColumn.TRANSPORT_ID.toString()),
                resultSet.getString(SqlColumn.TRANSPORT.toString())));
        tour.setDiscount(new TourDiscount(resultSet.getInt(SqlColumn.TOUR_DISCOUNT_ID.toString()),
                resultSet.getDouble(SqlColumn.TOUR_DISCOUNT_SIZE.toString())));
    }

    private void initializeStatementToCreateTour(PreparedStatement statement, Tour tour) throws SQLException {
        statement.setString(CREATE_TOUR_NAME_INDEX, tour.getName());
        statement.setDouble(CREATE_TOUR_COST_INDEX, tour.getCost());
        statement.setDate(CREATE_TOUR_DEPARTURE_DATE_INDEX, tour.getDepartureDate());
        statement.setInt(CREATE_TOUR_DAYS_INDEX, tour.getDays());
        statement.setInt(CREATE_TOUR_PLACES_INDEX, tour.getPlaces());
        statement.setInt(CREATE_TOUR_TYPE_INDEX, tour.getTourType().getTourTypeId());
        statement.setInt(CREATE_TOUR_CITY_INDEX, tour.getCity().getCityId());
        statement.setInt(CREATE_TOUR_DEPARTURE_CITY_INDEX, tour.getCity().getCityId());
        statement.setInt(CREATE_TOUR_HOTEL_INDEX, tour.getHotel().getHotelId());
        statement.setInt(CREATE_TOUR_TOURIST_INDEX, tour.getTourist().getTouristId());
        statement.setInt(CREATE_TOUR_TRANSPORT_INDEX, tour.getTransport().getTransportId());
        statement.setInt(CREATE_TOUR_DISCOUNT_INDEX, tour.getDiscount().getId());
    }

    private static final class TourDAOImplHolder {
        private static final TourDAOImpl INSTANCE = new TourDAOImpl();
    }
}
