package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.util.CommandUtil;
import edu.khai.voloshyn.travelagency.entity.Order;
import edu.khai.voloshyn.travelagency.entity.Tour;
import edu.khai.voloshyn.travelagency.entity.User;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetBoughtOrderCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SetBoughtOrderCommand.class.getName());

    private ServiceFactory serviceFactory = ServiceFactory.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            int orderId = Integer.parseInt(request.getParameter(JspParameterType.ORDER_ID));
            Order order = serviceFactory.getOrderService().findOrderById(orderId);
            User user = serviceFactory.getUserService().findUserById(order.getUser().getUserId());
            Tour tour = serviceFactory.getTourService().findTourById(order.getTour().getTourId());
            setBoughtActions(order, user, tour);
            new CommandUtil().setOrderSessionAttributes(request, user, tour);
            return new GetAllOrdersCommand().execute(request, response);
        } catch (ServiceException e) {
            LOGGER.error(Message.SET_BOUGHT_ORDER_COMMAND_ERROR, e);
        }
        return PageType.HOME_PAGE.getAddress();
    }

    private void setBoughtActions(Order order, User user, Tour tour) throws ServiceException {
        serviceFactory.getOrderService().setBoughtOrder(order.getOrderId());
    }
}
