package edu.khai.voloshyn.travelagency.factory;

import edu.khai.voloshyn.travelagency.dao.*;
import edu.khai.voloshyn.travelagency.dao.impl.*;

public class DAOFactory {

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return DAOFactoryHolder.INSTANCE;
    }

    public UserDAO getUserDAO() {
        return UserDAOImpl.getInstance();
    }

    public TourDAO getTourDAO() {
        return TourDAOImpl.getInstance();
    }

    public TourTypeDAO getTourTypeDAO() {
        return TourTypeDAOImpl.getInstance();
    }

    public CityDAO getCityDAO() {
        return CityDAOImpl.getInstance();
    }
    
    public HotelDAO getHotelDAO() {
        return HotelDAOImpl.getInstance();
    }
    
    public TouristDAO getTouristDAO() {
        return TouristDAOImpl.getInstance();
    }

    public TransportDAO getTransportDAO() {
        return TransportDAOImpl.getInstance();
    }

    public OrderDAO getOrderDAO() {
        return OrderDAOImpl.getInstance();
    }

    private static final class DAOFactoryHolder {
        private static final DAOFactory INSTANCE = new DAOFactory();
    }

}
