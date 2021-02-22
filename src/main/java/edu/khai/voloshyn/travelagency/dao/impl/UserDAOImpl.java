package edu.khai.voloshyn.travelagency.dao.impl;


import edu.khai.voloshyn.travelagency.dao.UserDAO;
import edu.khai.voloshyn.travelagency.dao.constants.SqlColumn;
import edu.khai.voloshyn.travelagency.dao.constants.SqlStatement;
import edu.khai.voloshyn.travelagency.entity.Discount;
import edu.khai.voloshyn.travelagency.entity.Role;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.pool.ConnectionPool;
import edu.khai.voloshyn.travelagency.pool.ProxyConnection;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class.getName());

    private static final int USER_ID_INDEX = 1;
    private static final int USER_LOGIN_INDEX = 1;
    private static final int USER_PASSWORD_INDEX = 2;
    private static final int CLIENT_ID_INDEX = 1;
    private static final int UPDATE_CASH_INDEX = 1;
    private static final int UPDATE_ID_INDEX = 2;
    private static final int CREATE_CLIENT_LOGIN_INDEX = 1;
    private static final int CREATE_CLIENT_PASSWORD_INDEX = 2;
    private static final int CREATE_CLIENT_NAME_INDEX = 3;
    private static final int CREATE_CLIENT_SURNAME_INDEX = 4;
    private static final int CREATE_CLIENT_CASH_INDEX = 5;
    private static final int CREATE_CLIENT_PHONE_INDEX = 6;
    private static final int CREATE_ADMIN_LOGIN_INDEX = 1;
    private static final int CREATE_ADMIN_PASSWORD_INDEX = 2;
    private static final int CREATE_ADMIN_NAME_INDEX = 3;
    private static final int CREATE_ADMIN_SURNAME_INDEX = 4;
    private static final int CREATE_ADMIN_PHONE_INDEX = 5;
    private static final int CREATE_ADMIN_ROLE_INDEX = 6;
    private static final int UPDATE_ADMIN_NAME_INDEX = 1;
    private static final int UPDATE_ADMIN_SURNAME_INDEX = 2;
    private static final int UPDATE_ADMIN_PHONE_INDEX = 3;
    private static final int UPDATE_ADMIN_ID_INDEX = 4;
    private static final int CREATE_MANAGER_LOGIN_INDEX = 1;
    private static final int CREATE_MANAGER_PASSWORD_INDEX = 2;
    private static final int CREATE_MANAGER_NAME_INDEX = 3;
    private static final int CREATE_MANAGER_SURNAME_INDEX = 4;
    private static final int CREATE_MANAGER_PHONE_INDEX = 5;
    private static final int CREATE_MANAGER_ROLE_INDEX = 6;
    private static final int UPDATE_MANAGER_NAME_INDEX = 1;
    private static final int UPDATE_MANAGER_SURNAME_INDEX = 2;
    private static final int UPDATE_MANAGER_PHONE_INDEX = 3;
    private static final int UPDATE_MANAGER_ID_INDEX = 4;
    private static final int UPDATE_CLIENT_NAME_INDEX = 1;
    private static final int UPDATE_CLIENT_SURNAME_INDEX = 2;
    private static final int UPDATE_CLIENT_PHONE_INDEX = 3;
    private static final int UPDATE_CLIENT_CASH_INDEX = 4;
    private static final int UPDATE_CLIENT_ID_INDEX = 5;

    private UserDAOImpl() {
    }

    public static UserDAO getInstance() {
        return UserDAOImplHolder.INSTANCE;
    }

    @Override
    public User findById(int userId) throws DAOException {
        User user = new User();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.FIND_USER_BY_ID)) {
            statement.setInt(USER_ID_INDEX, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    initializeUser(user, resultSet);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_USER_BY_ID_ERROR);
            throw new DAOException(Message.FIND_USER_BY_ID_ERROR, e);
        }
        return user;
    }

    @Override
    public String findLogin(String userLogin) throws DAOException {
        String login = null;
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CHECK_LOGIN_EXISTENCE)) {
            statement.setString(USER_LOGIN_INDEX, userLogin);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    login = resultSet.getString(SqlColumn.USER_LOGIN.toString());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_LOGIN_ERROR);
            throw new DAOException(Message.FIND_LOGIN_ERROR, e);
        }
        return login;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        User user = new User();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.GET_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(USER_LOGIN_INDEX, login);
            statement.setString(USER_PASSWORD_INDEX, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    initializeUser(user, resultSet);
                }
            }
            if (user.getPassword() == null || user.getLogin() == null) {
                throw new DAOException();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.FIND_USER_BY_LOGIN_AND_PASSWORD_ERROR);
            throw new DAOException(Message.FIND_USER_BY_LOGIN_AND_PASSWORD_ERROR, e);
        }
        return user;
    }

    @Override
    public User createClient(User user) throws DAOException {
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CREATE_CLIENT)) {
            initializeCreateClientStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_CLIENT_ERROR);
            throw new DAOException(Message.CREATE_CLIENT_ERROR, e);
        }
        return findUserByLoginAndPassword(user.getLogin(), String.valueOf(user.getPassword()));
    }

    @Override
    public void createAdmin(User user) throws DAOException {
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CREATE_ADMIN)) {
            initializeCreateAdminStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_ADMIN_ERROR);
            throw new DAOException(Message.CREATE_ADMIN_ERROR, e);
        }
    }
    
    @Override
    public void createManager(User user) throws DAOException {
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             PreparedStatement statement = connection.prepareStatement(SqlStatement.CREATE_MANAGER)) {
            initializeCreateManagerStatement(statement, user);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(Message.CREATE_MANAGER_ERROR);
            throw new DAOException(Message.CREATE_MANAGER_ERROR, e);
        }
    }

    @Override
    public void deleteClient(int clientId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.DELETE_CLIENT)) {
                statement.setInt(CLIENT_ID_INDEX, clientId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.DELETE_CLIENT_ERROR);
            throw new DAOException(Message.DELETE_CLIENT_ERROR, e);
        }
    }

    @Override
    public List<User> getAll() throws DAOException {
        List<User> listToReturn = new ArrayList<>();
        try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
             Statement statement = connection.createStatement()) {
            try (ResultSet resultSet = statement.executeQuery(SqlStatement.GET_ALL_USERS)) {
                while (resultSet.next()) {
                    User user = new User();
                    initializeUser(user, resultSet);
                    listToReturn.add(user);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(Message.GET_ALL_USERS_ERROR);
            throw new DAOException(Message.GET_ALL_USERS_ERROR, e);
        }
        return listToReturn;
    }

    @Override
    public void updateClient(User user) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_CLIENT_INFO)) {
                initializeUpdateClientStatement(statement, user);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_CLIENT_ERROR);
            throw new DAOException(Message.UPDATE_CLIENT_ERROR, e);
        }
    }

    @Override
    public void updateAdmin(User user) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_ADMIN_INFO)) {
                initializeUpdateAdminStatement(statement, user);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_ADMIN_ERROR);
            throw new DAOException(Message.UPDATE_ADMIN_ERROR, e);
        }
    }
    
    @Override
    public void updateManager(User user) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_MANAGER_INFO)) {
                initializeUpdateManagerStatement(statement, user);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UPDATE_MANAGER_ERROR);
            throw new DAOException(Message.UPDATE_MANAGER_ERROR, e);
        }
    }

    @Override
    public void blockClient(int clientId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.BLOCK_CLIENT)) {
                statement.setInt(CLIENT_ID_INDEX, clientId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.BLOCK_CLIENT_ERROR);
            throw new DAOException(Message.BLOCK_CLIENT_ERROR, e);
        }
    }

    @Override
    public void unblockClient(int clientId) throws DAOException {
        try {
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UNBLOCK_CLIENT)) {
                statement.setInt(CLIENT_ID_INDEX, clientId);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.error(Message.UNBLOCK_CLIENT_ERROR);
            throw new DAOException(Message.UNBLOCK_CLIENT_ERROR, e);
        }
    }

    @Override
    public void takeMoney(User user, double amount) throws DAOException {
        try {
            double updatedCash = user.getCash() - amount;
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_USER_CASH)) {
                statement.setDouble(UPDATE_CASH_INDEX, updatedCash);
                statement.setInt(UPDATE_ID_INDEX, user.getUserId());
                statement.executeUpdate();
            }
            user.setCash(updatedCash);
        } catch (SQLException e) {
            LOGGER.error(Message.TAKE_MONEY_ERROR);
            throw new DAOException(Message.TAKE_MONEY_ERROR, e);
        }
    }

    @Override
    public void returnMoney(User user, double amount) throws DAOException {
        try {
            double updatedCash = user.getCash() + amount;
            try (ProxyConnection connection = new ProxyConnection(ConnectionPool.INSTANCE.getConnection());
                 PreparedStatement statement = connection.prepareStatement(SqlStatement.UPDATE_USER_CASH)) {
                statement.setDouble(UPDATE_CASH_INDEX, updatedCash);
                statement.setInt(UPDATE_ID_INDEX, user.getUserId());
                statement.executeUpdate();
            }
            user.setCash(updatedCash);
        } catch (SQLException e) {
            LOGGER.error(Message.RETURN_MONEY_ERROR);
            throw new DAOException(Message.RETURN_MONEY_ERROR, e);
        }
    }

    private void initializeUser(User user, ResultSet resultSet) throws SQLException {
        user.setId(resultSet.getInt(SqlColumn.USER_ID.toString()));
        user.setLogin(resultSet.getString(SqlColumn.USER_LOGIN.toString()));
        user.setPassword(resultSet.getString(SqlColumn.USER_PASSWORD.toString()).toCharArray());
        user.setName(resultSet.getString(SqlColumn.USER_NAME.toString()));
        user.setSurname(resultSet.getString(SqlColumn.USER_SURNAME.toString()));
        user.setDiscount(new Discount(resultSet.getInt(SqlColumn.USER_DISCOUNT_ID.toString()),
                resultSet.getDouble(SqlColumn.USER_DISCOUNT_SIZE.toString())));
        user.setCash(resultSet.getDouble(SqlColumn.USER_CASH.toString()));
        user.setPhone(resultSet.getString(SqlColumn.USER_PHONE.toString()));
        user.setRole(Role.valueOf(resultSet.getString(SqlColumn.USER_ROLE.toString()).toUpperCase()));
    }

    private void initializeCreateClientStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(CREATE_CLIENT_LOGIN_INDEX, user.getLogin());
        statement.setString(CREATE_CLIENT_PASSWORD_INDEX, String.valueOf(user.getPassword()));
        statement.setString(CREATE_CLIENT_NAME_INDEX, user.getName());
        statement.setString(CREATE_CLIENT_SURNAME_INDEX, user.getSurname());
        statement.setDouble(CREATE_CLIENT_CASH_INDEX, user.getCash());
        statement.setString(CREATE_CLIENT_PHONE_INDEX, user.getPhone());
    }

    private void initializeCreateAdminStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(CREATE_ADMIN_LOGIN_INDEX, user.getLogin());
        statement.setString(CREATE_ADMIN_PASSWORD_INDEX, String.valueOf(user.getPassword()));
        statement.setString(CREATE_ADMIN_NAME_INDEX, user.getName());
        statement.setString(CREATE_ADMIN_SURNAME_INDEX, user.getSurname());
        statement.setString(CREATE_ADMIN_PHONE_INDEX, user.getPhone());
        statement.setInt(CREATE_ADMIN_ROLE_INDEX, user.getRole().getId());
    }
    
    private void initializeCreateManagerStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(CREATE_MANAGER_LOGIN_INDEX, user.getLogin());
        statement.setString(CREATE_MANAGER_PASSWORD_INDEX, String.valueOf(user.getPassword()));
        statement.setString(CREATE_MANAGER_NAME_INDEX, user.getName());
        statement.setString(CREATE_MANAGER_SURNAME_INDEX, user.getSurname());
        statement.setString(CREATE_MANAGER_PHONE_INDEX, user.getPhone());
        statement.setInt(CREATE_MANAGER_ROLE_INDEX, user.getRole().getId());
    }

    private void initializeUpdateClientStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(UPDATE_CLIENT_NAME_INDEX, user.getName());
        statement.setString(UPDATE_CLIENT_SURNAME_INDEX, user.getSurname());
        statement.setString(UPDATE_CLIENT_PHONE_INDEX, user.getPhone());
        statement.setDouble(UPDATE_CLIENT_CASH_INDEX, user.getCash());
        statement.setInt(UPDATE_CLIENT_ID_INDEX, user.getUserId());
    }

    private void initializeUpdateAdminStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(UPDATE_ADMIN_NAME_INDEX, user.getName());
        statement.setString(UPDATE_ADMIN_SURNAME_INDEX, user.getSurname());
        statement.setString(UPDATE_ADMIN_PHONE_INDEX, user.getPhone());
        statement.setInt(UPDATE_ADMIN_ID_INDEX, user.getUserId());
    }
    
    private void initializeUpdateManagerStatement(PreparedStatement statement, User user) throws SQLException {
        statement.setString(UPDATE_MANAGER_NAME_INDEX, user.getName());
        statement.setString(UPDATE_MANAGER_SURNAME_INDEX, user.getSurname());
        statement.setString(UPDATE_MANAGER_PHONE_INDEX, user.getPhone());
        statement.setInt(UPDATE_MANAGER_ID_INDEX, user.getUserId());
    }

    @Override
    public void update(User userId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void create(User user) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(User user) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    @Override
    public void delete(int userId) throws DAOException {
        throw new DAOException(new UnsupportedOperationException(
                Message.UNSUPPORTED_OPERATION));
    }

    private static final class UserDAOImplHolder {
        private static final UserDAOImpl INSTANCE = new UserDAOImpl();
    }
}
