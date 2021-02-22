package edu.khai.voloshyn.travelagency.dao.impl;

import edu.khai.voloshyn.travelagency.dao.TransportDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.Transport;
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

public class TransportDAOImpl implements TransportDAO {
    private static final Logger LOGGER = LogManager.getLogger(TransportDAOImpl.class.getName());

    private static final int TRANSPORT_ID_INDEX = 1;

    private TransportDAOImpl() {
    }

    public static TransportDAO getInstance() {
        return TransportDAOImplHolder.INSTANCE;
    }

    @Override
    public Transport findById(int transportId) throws DAOException {
        Transport transport = new Transport();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_TRANSPORT_BY_ID)) {
            statement.setInt(TRANSPORT_ID_INDEX, transportId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeTransport(transport, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_TRANSPORT_BY_ID_ERROR);
            throw new DAOException(Message.FIND_TRANSPORT_BY_ID_ERROR, e);
        }
        return transport;
    }

    @Override
    public List<Transport> getAll() throws DAOException {
        List<Transport> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_ALL_TRANSPORT)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Transport transport = new Transport();
                    initializeTransport(transport, resultSet);
                    listToReturn.add(transport);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_TRANSPORTS_ERROR);
            throw new DAOException(Message.GET_ALL_TRANSPORTS_ERROR, e);
        }
        return listToReturn;
    }

    private void initializeTransport(Transport transport, ResultSet resultSet) throws SQLException {
        transport.setTransportId(resultSet.getInt(SqlColumn.TRANSPORT_ID.toString()));
        transport.setType(resultSet.getString(SqlColumn.TRANSPORT.toString()));
    }

    @Override
    public void update(Transport transportId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void create(Transport transport) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(Transport transport) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(int transportId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class TransportDAOImplHolder {
        private static final TransportDAOImpl INSTANCE = new TransportDAOImpl();
    }
}
