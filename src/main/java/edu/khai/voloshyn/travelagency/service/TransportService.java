package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.Transport;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

public interface TransportService {
    Transport findTransportById(int transportId) throws ServiceException;

    List<Transport> getAllTransports() throws ServiceException;
}
