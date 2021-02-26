package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.TouristDAO;
import edu.khai.voloshyn.travelagency.entity.Tourist;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.TouristService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.ProperNameValidator;
import edu.khai.voloshyn.travelagency.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TouristServiceImpl implements TouristService {
    private static final Logger LOGGER = LogManager.getLogger(TouristServiceImpl.class.getName());

    private TouristDAO touristDAO = DAOFactory.getInstance().getTouristDAO();

    private TouristServiceImpl() {
    }

    public static TouristService getInstance() {
        return TouristServiceImpl.TouristServiceImplHolder.INSTANCE;
    }

    @Override
    public void createTourist(Tourist tourist) throws ServiceException {
        Validator validator = new ProperNameValidator(tourist.getTourist());
        try {
            validator.validate();
            if (checkTouristExistence(tourist.getTourist())) {
                touristDAO.create(tourist);
            }
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.CREATE_TOURIST_ERROR);
            throw new ServiceException(Message.CREATE_TOURIST_ERROR, e);
        }
    }

    @Override
    public void deleteTouristById(int touristId) throws ServiceException {
        try {
            touristDAO.delete(touristId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_TOURIST_ERROR);
            throw new ServiceException(Message.DELETE_TOURIST_ERROR, e);
        }
    }

    @Override
    public Tourist findTouristById(int touristId) throws ServiceException {
        try {
            return touristDAO.findById(touristId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_TOURIST_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_TOURIST_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<Tourist> getAllTourists() throws ServiceException {
        try {
            return touristDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_TOURISTS_ERROR);
            throw new ServiceException(Message.GET_ALL_TOURISTS_ERROR, e);
        }
    }

    private boolean checkTouristExistence(String tourist) throws DAOException {
        return touristDAO.findTourist(tourist) == null;
    }

    private static final class TouristServiceImplHolder {
        private static final TouristServiceImpl INSTANCE = new TouristServiceImpl();
    }
}
