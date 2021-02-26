package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.TourDiscountDAO;
import edu.khai.voloshyn.travelagency.entity.TourDiscount;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.TourDiscountService;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class TourDiscountServiceImpl implements TourDiscountService {
    private static final Logger LOGGER = LogManager.getLogger(TourDiscountServiceImpl.class.getName());

    private TourDiscountDAO tourDiscountDAO = DAOFactory.getInstance().getTourDiscountDAO();

    private TourDiscountServiceImpl() {
    }

    public static TourDiscountServiceImpl getInstance() {
        return TourDiscountServiceImpl.TourDiscountServiceImplHolder.INSTANCE;
    }

    @Override
    public TourDiscount findTourDiscountById(int tourDiscountId) throws ServiceException {
        try {
            return tourDiscountDAO.findById(tourDiscountId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_TOUR_DISCOUNT_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_TOUR_DISCOUNT_BY_ID_ERROR, e);
        }
    }

    @Override
    public List<TourDiscount> getAllTourDiscounts() throws ServiceException {
        try {
            return tourDiscountDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_TOUR_DISCOUNTS_ERROR);
            throw new ServiceException(Message.GET_ALL_TOUR_DISCOUNTS_ERROR, e);
        }
    }

    private static final class TourDiscountServiceImplHolder {
        private static final TourDiscountServiceImpl INSTANCE = new TourDiscountServiceImpl();
    }
}
