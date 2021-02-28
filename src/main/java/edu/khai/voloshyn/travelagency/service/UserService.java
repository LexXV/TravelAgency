package edu.khai.voloshyn.travelagency.service;

import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;

import java.util.List;

/**
 * The interface User Service.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface UserService {
    User signIn(String login, String password) throws ServiceException;

    User findUserById(int userId) throws ServiceException;

    User signUp(String login, String password, String name, String surname, String cash, String phone) throws ServiceException;

    void createAdmin(String login, String password, String name, String surname, String phone) throws ServiceException;
    
    void createManager(String login, String password, String name, String surname, String phone) throws ServiceException;

    void deleteClient(int clientId) throws ServiceException;

    void blockClient(int clientId) throws ServiceException;

    void unblockClient(int clientId) throws ServiceException;

    void updateAdmin(User user) throws ServiceException;
    
    void updateManager(User user) throws ServiceException;

    void updateClient(User user) throws ServiceException;

    List<User> getAllUsers() throws ServiceException;

    void takeMoney(User user, double amount) throws ServiceException;

    void returnMoney(User user, double amount) throws ServiceException;
}
