package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.TourTypeDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.TourType;
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

public class TourTypeDAOImpl implements TourTypeDAO {
    private static final Logger LOGGER = LogManager.getLogger(TourTypeDAOImpl.class.getName());

    private static final int TOUR_TYPE_INDEX = 1;
    private static final int TOUR_TYPE_ID_INDEX = 1;

    private TourTypeDAOImpl() {
    }

    public static TourTypeDAO getInstance() {
        return TourTypeDAOImplHolder.INSTANCE;
    }

    @Override
    public void create(TourType tourType) throws DAOException {
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CREATE_TOUR_TYPE)) {
            statement.setString(TOUR_TYPE_INDEX, tourType.getType());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_TOUR_TYPE_ERROR);
            throw new DAOException(Message.CREATE_TOUR_TYPE_ERROR, e);
        }
    }

    @Override
    public void delete(int tourTypeId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_TOUR_TYPE)) {
                statement.setInt(TOUR_TYPE_ID_INDEX, tourTypeId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_TOUR_TYPE_ERROR);
            throw new DAOException(Message.DELETE_TOUR_TYPE_ERROR, e);
        }
    }

    @Override
    public TourType findById(int tourTypeId) throws DAOException {
        TourType tourType = new TourType();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TOUR_TYPE_BY_ID)) {
            statement.setInt(TOUR_TYPE_ID_INDEX, tourTypeId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeTourType(tourType, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOUR_TYPE_BY_ID_ERROR);
            throw new DAOException(Message.FIND_TOUR_TYPE_BY_ID_ERROR, e);
        }
        return tourType;
    }

    @Override
    public List<TourType> getAll() throws DAOException {
        List<TourType> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TOUR_TYPES)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TourType tourType = new TourType();
                    initializeTourType(tourType, resultSet);
                    listToReturn.add(tourType);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_TOUR_TYPES_ERROR);
            throw new DAOException(Message.GET_ALL_TOUR_TYPES_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public String findTourType(String tourType) throws DAOException {
        String type = null;
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CHECK_TOUR_TYPE_EXISTENCE)) {
            statement.setString(TOUR_TYPE_INDEX, tourType);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    type = resultSet.getString(SqlColumn.TOUR_TYPE.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TOUR_TYPE_ERROR);
            throw new DAOException(Message.FIND_TOUR_TYPE_ERROR, e);
        }
        return type;
    }

    private void initializeTourType(TourType tourType, ResultSet resultSet) throws SQLException {
        tourType.setTourTypeId(resultSet.getInt(SqlColumn.TOUR_TYPE_ID.toString()));
        tourType.setType(resultSet.getString(SqlColumn.TOUR_TYPE.toString()));
    }

    @Override
    public void update(TourType tourTypeId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(TourType item) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class TourTypeDAOImplHolder {
        private static final TourTypeDAOImpl INSTANCE = new TourTypeDAOImpl();
    }
}
