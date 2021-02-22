package edu.khai.voloshyn.travelagency.pool;

import edu.khai.voloshyn.travelagency.exception.ConnectionPoolException;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;

public enum ConnectionPool {
    INSTANCE;

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class.getName());
    private static final String DATABASE_PROPERTIES_FILENAME = "db.properties";
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";
    private static final int DEFAULT_POOL_SIZE = 16;

    private BlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> usedConnections;
    private AtomicBoolean isPoolInitiated;

    ConnectionPool() {
        isPoolInitiated = new AtomicBoolean(false);
        freeConnections = new LinkedBlockingDeque<>(DEFAULT_POOL_SIZE);
        usedConnections = new ArrayDeque<>();
    }

    public void initConnectionPool() throws ConnectionPoolException {
        if (!isPoolInitiated.get()) {
            Properties properties = new Properties();
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(DATABASE_PROPERTIES_FILENAME);
            try {
                properties.load(Objects.requireNonNull(inputStream));
                String user = properties.getProperty(DATABASE_USER);
                char[] password = properties.getProperty(DATABASE_PASSWORD).toCharArray();
                String url = properties.getProperty(DATABASE_URL);
                String driver = properties.getProperty(DATABASE_DRIVER);
                Class.forName(driver);
                for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                    freeConnections.add(new ProxyConnection(DriverManager.getConnection(url, user, new String(password))));
                }
                isPoolInitiated.set(true);
            } catch (ClassNotFoundException | IOException | SQLException e) {
                LOGGER.error(Message.INITIALIZE_CONNECTION_POOL_ERROR);
                throw new ConnectionPoolException(Message.INITIALIZE_CONNECTION_POOL_ERROR, e);
            }
        }
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            usedConnections.add(connection);
        } catch (InterruptedException e) {
            LOGGER.error(Message.GET_CONNECTION_ERROR);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        if (connection != null) {
            usedConnections.remove(connection);
            boolean tmp = freeConnections.add(connection);
            if (!tmp) {
                LOGGER.info(Message.RELEASE_CONNECTION_ERROR);
            }
        }
    }

    public void destroyPool() throws InterruptedException {
        if (isPoolInitiated.get()) {
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                freeConnections.take().closePool();
            }
        }
        deregisterDrivers();
    }

    private void deregisterDrivers() {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            try {
                DriverManager.deregisterDriver(drivers.nextElement());
            } catch (SQLException e) {
                LOGGER.error(Message.DEREGISTER_DRIVERS_ERROR);
            }
        }
    }
}
