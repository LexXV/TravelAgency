package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Transport;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface Transport Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface TransportService {
    Transport findTransportById(int transportId) throws ServiceException;

    List<Transport> getAllTransports() throws ServiceException;
}
