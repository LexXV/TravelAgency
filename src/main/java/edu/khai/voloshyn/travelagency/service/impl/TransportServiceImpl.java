package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.TransportDAO;
import edu.khai.voloshyn.travelagency.entity.Transport;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.TransportService;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TransportServiceImpl implements TransportService {
    private static final Logger LOGGER = LogManager.getLogger(TransportServiceImpl.class.getName());

    private TransportDAO transportDAO = DAOFactory.getInstance().getTransportDAO();

    private TransportServiceImpl() {
    }

    public static TransportServiceImpl getInstance() {
        return TransportServiceImpl.TransportServiceImplHolder.INSTANCE;
    }

    @Override
    public Transport findTransportById(int transportId) throws ServiceException {
        try {
            return transportDAO.findById(transportId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_TRANSPORT_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_TRANSPORT_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<Transport> getAllTransports() throws ServiceException {
        try {
            return transportDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_TRANSPORTS_ERROR);
            throw new ServiceException(Message.GET_ALL_TRANSPORTS_ERROR, e);
        }
    }

    private static final class TransportServiceImplHolder {
        private static final TransportServiceImpl INSTANCE = new TransportServiceImpl();
    }
}
