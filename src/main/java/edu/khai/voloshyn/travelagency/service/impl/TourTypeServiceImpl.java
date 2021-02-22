package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.TourTypeDAO;
import edu.khai.voloshyn.travelagency.entity.TourType;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.TourTypeService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.ProperNameValidator;
import edu.khai.voloshyn.travelagency.validator.Validator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourTypeServiceImpl implements TourTypeService {
    private static final Logger LOGGER = LogManager.getLogger(TourTypeServiceImpl.class.getName());

    private TourTypeDAO tourTypeDAO = DAOFactory.getInstance().getTourTypeDAO();

    private TourTypeServiceImpl() {
    }

    public static TourTypeService getInstance() {
        return TourTypeServiceImpl.TourTypeServiceImplHolder.INSTANCE;
    }

    @Override
    public void createTourType(TourType tourType) throws ServiceException {
        Validator validator = new ProperNameValidator(tourType.getType());
        try {
            validator.validate();
            if (checkTourTypeExistence(tourType.getType())) {
                tourTypeDAO.create(tourType);
            }
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.CREATE_TOUR_TYPE_ERROR);
            throw new ServiceException(Message.CREATE_TOUR_ERROR, e);
        }
    }

    @Override
    public void deleteTourTypeById(int tourTypeId) throws ServiceException {
        try {
            tourTypeDAO.delete(tourTypeId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_TOUR_TYPE_ERROR);
            throw new ServiceException(Message.DELETE_TOUR_TYPE_ERROR, e);
        }
    }

    @Override
    public TourType findTourTypeById(int tourTypeId) throws ServiceException {
        try {
            return tourTypeDAO.findById(tourTypeId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_TOUR_TYPE_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_TOUR_TYPE_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<TourType> getAllTourTypes() throws ServiceException {
        try {
            return tourTypeDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_TOUR_TYPES_ERROR);
            throw new ServiceException(Message.GET_ALL_TOUR_TYPES_ERROR, e);
        }
    }

    private boolean checkTourTypeExistence(String tourType) throws DAOException {
        return tourTypeDAO.findTourType(tourType) == null;
    }

    private static final class TourTypeServiceImplHolder {
        private static final TourTypeServiceImpl INSTANCE = new TourTypeServiceImpl();
    }

}
