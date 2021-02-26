package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.TouristDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.Tourist;
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

public class TouristDAOImpl implements TouristDAO {
    private static final Logger LOGGER = LogManager.getLogger(TouristDAOImpl.class.getName());

    private static final int TOURIST_INDEX = 1;
    private static final int TOURIST_ID_INDEX = 1;

    private TouristDAOImpl() {
    }

    public static TouristDAO getInstance() {
        return TouristDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(Tourist tourist) throws DAOException {
        try (ProxyConnection connection =
                     new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement =
                     connection.prepareStatement(SqlStatement.CREATE_TOURIST)) {
            statement.setString(TOURIST_INDEX, tourist.getTourist());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_TOURIST_ERROR);
            throw new DAOException(Message.CREATE_TOURIST_ERROR, e);
        }
    }

    @Override
    public void delete(int touristId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_TOURIST)) {
                statement.setInt(TOURIST_ID_INDEX, touristId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_TOURIST_ERROR);
            throw new DAOException(Message.DELETE_TOURIST_ERROR, e);
        }
    }

    @Override
    public Tourist findById(int touristId) throws DAOException {
        Tourist tourist = new Tourist();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TOURIST_BY_ID)) {
            statement.setInt(TOURIST_ID_INDEX, touristId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeTourist(tourist, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOURIST_BY_ID_ERROR);
            throw new DAOException(Message.FIND_TOURIST_BY_ID_ERROR, e);
        }
        return tourist;
    }

    @Override
    public List<Tourist> getAll() throws DAOException {
        List<Tourist> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOURISTS)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tourist tourist = new Tourist();
                    initializeTourist(tourist, resultSet);
                    listToReturn.add(tourist);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_TOURISTS_ERROR);
            throw new DAOException(Message.GET_ALL_TOURISTS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public String findTourist(String tourist) throws DAOException {
        String touristToReturn = null;
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CHECK_TOURIST_EXISTENCE)) {
            statement.setString(TOURIST_INDEX, tourist);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    touristToReturn = resultSet.getString(SqlColumn.TOURIST.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOURIST_ERROR);
            throw new DAOException(Message.FIND_TOURIST_ERROR, e);
        }
        return touristToReturn;
    }

    private void initializeTourist(Tourist tourist, ResultSet resultSet) throws SQLException {
        tourist.setTouristId(resultSet.getInt(SqlColumn.TOURIST_ID.toString()));
        tourist.setTourist(resultSet.getString(SqlColumn.TOURIST.toString()));
    }

    @Override
    public void update(Tourist touristId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(Tourist tourist) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class TouristDAOImplHolder {
        private static final TouristDAOImpl INSTANCE = new TouristDAOImpl();
    }
}
