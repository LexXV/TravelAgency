package edu.khai.voloshyn.travelagency.dao;

import edu.khai.voloshyn.travelagency.exception.DAOException;

import java.util.List;

/**
 * The interface DAO.
 *
 * @param <T> the type parameter
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface DAO<T> {
    void create(T item) throws DAOException;

    void delete(T item) throws DAOException;

    void delete(int id) throws DAOException;

    T findById(int id) throws DAOException;

    List<T> getAll() throws DAOException;

    void update(T id) throws DAOException;
}
