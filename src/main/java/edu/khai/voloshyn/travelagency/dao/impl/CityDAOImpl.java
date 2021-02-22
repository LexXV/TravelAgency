package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.CityDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.City;
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

public class CityDAOImpl implements CityDAO {
    private static final Logger LOGGER = LogManager.getLogger(CityDAOImpl.class.getName());

    private static final int CITY_INDEX = 1;
    private static final int CITY_ID_INDEX = 1;

    private CityDAOImpl() {
    }

    public static CityDAO getInstance() {
        return CityDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(City city) throws DAOException {
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.CREATE_CITY)) {
            statement.setString(CITY_INDEX, city.getCity());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_CITY_ERROR);
            throw new DAOException(Message.CREATE_CITY_ERROR, e);
        }
    }

    @Override
    public void delete(int cityId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_CITY)) {
                statement.setInt(CITY_ID_INDEX, cityId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_CITY_ERROR);
            throw new DAOException(Message.DELETE_CITY_ERROR, e);
        }
    }

    @Override
    public City findById(int cityId) throws DAOException {
        City city = new City();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_CITY_BY_ID)) {
            statement.setInt(CITY_ID_INDEX, cityId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeCity(city, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_CITY_BY_ID_ERROR);
            throw new DAOException(Message.FIND_CITY_BY_ID_ERROR, e);
        }
        return city;
    }

    @Override
    public List<City> getAll() throws DAOException {
        List<City> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_CITIES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    City city = new City();
                    initializeCity(city, resultSet);
                    listToReturn.add(city);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_CITIES_ERROR);
            throw new DAOException(Message.GET_ALL_CITIES_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public String findCity(String city) throws DAOException {
        String cityToReturn = null;
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CHECK_CITY_EXISTENCE)) {
            statement.setString(CITY_INDEX, city);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    cityToReturn = resultSet.getString(SqlColumn.CITY.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_CITY_ERROR);
            throw new DAOException(Message.FIND_CITY_ERROR, e);
        }
        return cityToReturn;
    }

    private void initializeCity(City city, ResultSet resultSet) throws SQLException {
        city.setCityId(resultSet.getInt(SqlColumn.CITY_ID.toString()));
        city.setCity(resultSet.getString(SqlColumn.CITY.toString()));
    }

    @Override
    public void update(City cityId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(City city) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class CityDAOImplHolder {
        private static final CityDAOImpl INSTANCE = new CityDAOImpl();
    }
}
