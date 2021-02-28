package edu.khai.voloshyn.travelagency.controller;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.exception.ConnectionPoolException;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.CommandFactory;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.pool.ConnectionPool;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
@MultipartConfig
@WebServlet(urlPatterns = {""})
public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class.getName());

    @Override
    public void init() {
        try {
            ConnectionPool.INSTANCE.initConnectionPool();
        } catch (ConnectionPoolException e) {
            LOGGER.error(Message.SERVLET_INIT_ERROR, e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleRequest(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.SERVLET_DO_GET_ERROR, e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            handleRequest(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.SERVLET_DO_POST_ERROR, e);
        }
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServiceException {
        ServiceFactory.getInstance().getTourService().updateArchivedTours();
        ServiceFactory.getInstance().getOrderService().updateOrdersStatus();
        Command command = CommandFactory.getInstance().getCommand(request.getParameter(JspParameterType.COMMAND));
        String nextPage = command.execute(request, response);
        request.getSession().setAttribute(SessionAttribute.PAGE, nextPage);
        request.getRequestDispatcher(nextPage).forward(request, response);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (InterruptedException e) {
            LOGGER.error(Message.SERVLET_DESTROY_ERROR, e);
        }
        super.destroy();
        LOGGER.debug(Message.SERVLET_DESTROY);
    }
}
