package edu.khai.voloshyn.travelagency.service.impl;

import edu.khai.voloshyn.travelagency.dao.UserDAO;
import edu.khai.voloshyn.travelagency.entity.Role;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.DAOException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.exception.ValidatorException;
import edu.khai.voloshyn.travelagency.factory.DAOFactory;
import edu.khai.voloshyn.travelagency.service.UserService;
import edu.khai.voloshyn.travelagency.util.Message;
import edu.khai.voloshyn.travelagency.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class.getName());

    private UserDAO userDAO = DAOFactory.getInstance().getUserDAO();

    private UserServiceImpl() {
    }

    public static UserService getInstance() {
        return UserServiceImplHolder.INSTANCE;
    }

    @Override
    public User signIn(String login, String password) throws ServiceException {
        Validator validator = createSignInParametersValidator(login, password);
        try {
            validator.validate();
            User user = userDAO.findUserByLoginAndPassword(login, password);
            if (user != null) {
                return user;
            } else {
                LOGGER.error(Message.NO_SUCH_USER_ERROR);
                throw new ServiceException(Message.NO_SUCH_USER_ERROR);
            }
        } catch (ValidatorException | DAOException e) {
            LOGGER.error(Message.SIGN_IN_ERROR);
            throw new ServiceException(Message.SIGN_IN_ERROR, e);
        }
    }

    @Override
    public void createAdmin(String login, String password, String name, String surname, String phone) throws ServiceException {
        Validator validator = createSignUpAdminParametersValidator(login, password, name, surname, phone);
        try {
            validator.validate();
            if (checkLoginExistence(login)) {
                User user = new User(login, password.toCharArray(), name, surname, phone, Role.ADMIN);
                userDAO.createAdmin(user);
            } else {
                LOGGER.error(Message.LOGIN_ALREADY_EXIST_ERROR);
                throw new ServiceException(Message.LOGIN_ALREADY_EXIST_ERROR);
            }
        } catch (ValidatorException | DAOException e) {
            LOGGER.error(Message.CREATE_ADMIN_ERROR);
            throw new ServiceException(Message.CREATE_ADMIN_ERROR, e);
        }
    }
    
    @Override
    public void createManager(String login, String password, String name, String surname, String phone) throws ServiceException {
        Validator validator = createSignUpManagerParametersValidator(login, password, name, surname, phone);
        try {
            validator.validate();
            if (checkLoginExistence(login)) {
                User user = new User(login, password.toCharArray(), name, surname, phone, Role.MANAGER);
                userDAO.createManager(user);
            } else {
                LOGGER.error(Message.LOGIN_ALREADY_EXIST_ERROR);
                throw new ServiceException(Message.LOGIN_ALREADY_EXIST_ERROR);
            }
        } catch (ValidatorException | DAOException e) {
            LOGGER.error(Message.CREATE_MANAGER_ERROR);
            throw new ServiceException(Message.CREATE_MANAGER_ERROR, e);
        }
    }

    @Override
    public User findUserById(int userId) throws ServiceException {
        try {
            return userDAO.findById(userId);
        } catch (DAOException e) {
            LOGGER.error(Message.FIND_USER_BY_ID_ERROR);
            throw new ServiceException(Message.FIND_USER_BY_ID_ERROR, e);
        }
    }

    @Override
    public User signUp(String login, String password, String name, String surname, String cash, String phone) throws ServiceException {
        Validator validator = createSignUpParametersValidator
                (login, password, name, surname, Float.parseFloat(cash), phone);
        try {
            validator.validate();
            if (checkLoginExistence(login)) {
                return userDAO.createClient(new User(login, password.toCharArray(),
                        name, surname, Float.parseFloat(cash), phone));
            } else {
                LOGGER.error(Message.LOGIN_ALREADY_EXIST_ERROR);
                throw new ServiceException(Message.LOGIN_ALREADY_EXIST_ERROR);
            }
        } catch (ValidatorException | DAOException e) {
            LOGGER.error(Message.SIGN_UP_ERROR);
            throw new ServiceException(Message.SIGN_UP_ERROR, e);
        }
    }

    @Override
    public void deleteClient(int clientId) throws ServiceException {
        try {
            userDAO.deleteClient(clientId);
        } catch (DAOException e) {
            LOGGER.error(Message.DELETE_CLIENT_ERROR);
            throw new ServiceException(Message.DELETE_CLIENT_ERROR, e);
        }
    }

    @Override
    public void blockClient(int clientId) throws ServiceException {
        try {
            userDAO.blockClient(clientId);
        } catch (DAOException e) {
            LOGGER.error(Message.BLOCK_CLIENT_ERROR);
            throw new ServiceException(Message.BLOCK_CLIENT_ERROR, e);
        }
    }

    @Override
    public void unblockClient(int clientId) throws ServiceException {
        try {
            userDAO.unblockClient(clientId);
        } catch (DAOException e) {
            LOGGER.error(Message.UNBLOCK_CLIENT_ERROR);
            throw new ServiceException(Message.UNBLOCK_CLIENT_ERROR, e);
        }
    }


    @Override
    public List<User> getAllUsers() throws ServiceException {
        try {
            return userDAO.getAll();
        } catch (DAOException e) {
            LOGGER.error(Message.GET_ALL_USERS_ERROR);
            throw new ServiceException(Message.GET_ALL_USERS_ERROR, e);
        }
    }

    @Override
    public void updateAdmin(User user) throws ServiceException {
        Validator validator = createUpdateAdminParametersValidator(user.getName(),
                user.getSurname(), user.getPhone());
        try {
            validator.validate();
            userDAO.updateAdmin(user);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.UPDATE_ADMIN_ERROR);
            throw new ServiceException(Message.UPDATE_ADMIN_ERROR, e);
        }
    }
    
    @Override
    public void updateManager(User user) throws ServiceException {
        Validator validator = createUpdateManagerParametersValidator(user.getName(),
                user.getSurname(), user.getPhone());
        try {
            validator.validate();
            userDAO.updateManager(user);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.UPDATE_MANAGER_ERROR);
            throw new ServiceException(Message.UPDATE_MANAGER_ERROR, e);
        }
    }

    @Override
    public void updateClient(User user) throws ServiceException {
        Validator validator = createUpdateParametersValidator(user.getName(),
                user.getSurname(), user.getCash(), user.getPhone());
        try {
            validator.validate();
            userDAO.updateClient(user);
        } catch (DAOException | ValidatorException e) {
            LOGGER.error(Message.UPDATE_CLIENT_ERROR);
            throw new ServiceException(Message.UPDATE_CLIENT_ERROR, e);
        }
    }

    @Override
    public void takeMoney(User user, double amount) throws ServiceException {
        try {
            userDAO.takeMoney(user, amount);
        } catch (DAOException e) {
            LOGGER.error(Message.TAKE_MONEY_ERROR);
            throw new ServiceException(Message.TAKE_MONEY_ERROR, e);
        }
    }

    @Override
    public void returnMoney(User user, double amount) throws ServiceException {
        try {
            userDAO.returnMoney(user, amount);
        } catch (DAOException e) {
            LOGGER.error(Message.RETURN_MONEY_ERROR);
            throw new ServiceException(Message.RETURN_MONEY_ERROR, e);
        }
    }

    private Validator createSignInParametersValidator(String login, String password) {
        Validator loginValidator = new LoginValidator(login);
        Validator passwordValidator = new PasswordValidator(password);
        loginValidator.setNext(passwordValidator);
        return loginValidator;
    }

    private Validator createSignUpParametersValidator(String login, String password, String name, String surname, float cash, String phone) {
        Validator loginValidator = new LoginValidator(login);
        Validator passwordValidator = new PasswordValidator(password);
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator cashValidator = new MoneyValidator(cash);
        Validator phoneValidator = new PhoneValidator(phone);
        loginValidator.setNext(passwordValidator);
        passwordValidator.setNext(nameValidator);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(cashValidator);
        cashValidator.setNext(phoneValidator);
        return loginValidator;
    }

    private Validator createSignUpAdminParametersValidator(String login, String password, String name, String surname, String phone) {
        Validator loginValidator = new LoginValidator(login);
        Validator passwordValidator = new PasswordValidator(password);
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator phoneValidator = new PhoneValidator(phone);
        loginValidator.setNext(passwordValidator);
        passwordValidator.setNext(nameValidator);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(phoneValidator);
        return loginValidator;
    }
    
    private Validator createSignUpManagerParametersValidator(String login, String password, String name, String surname, String phone) {
        Validator loginValidator = new LoginValidator(login);
        Validator passwordValidator = new PasswordValidator(password);
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator phoneValidator = new PhoneValidator(phone);
        loginValidator.setNext(passwordValidator);
        passwordValidator.setNext(nameValidator);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(phoneValidator);
        return loginValidator;
    }

    private Validator createUpdateParametersValidator(String name, String surname, double cash, String phone) {
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator cashValidator = new MoneyValidator(cash);
        Validator phoneValidator = new PhoneValidator(phone);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(cashValidator);
        cashValidator.setNext(phoneValidator);
        return nameValidator;
    }

    private Validator createUpdateAdminParametersValidator(String name, String surname, String phone) {
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator phoneValidator = new PhoneValidator(phone);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(phoneValidator);
        return nameValidator;
    }
    
    private Validator createUpdateManagerParametersValidator(String name, String surname, String phone) {
        Validator nameValidator = new ProperNameValidator(name);
        Validator surnameValidator = new ProperNameValidator(surname);
        Validator phoneValidator = new PhoneValidator(phone);
        nameValidator.setNext(surnameValidator);
        surnameValidator.setNext(phoneValidator);
        return nameValidator;
    }

    private boolean checkLoginExistence(String userLogin) throws DAOException {
        return userDAO.findLogin(userLogin) == null;
    }

    private static final class UserServiceImplHolder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }
}
