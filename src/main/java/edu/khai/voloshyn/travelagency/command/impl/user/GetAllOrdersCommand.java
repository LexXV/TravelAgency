package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.entity.Order;
import edu.khai.voloshyn.travelagency.exception.ServiceException;
import edu.khai.voloshyn.travelagency.factory.ServiceFactory;
import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class GetAllOrdersCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetAllOrdersCommand.class.getName());

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> listResults = new ArrayList<>();
        try {
            listResults = ServiceFactory.getInstance().getOrderService().getAllOrders();
        } catch (ServiceException e) {
            LOGGER.error(Message.GET_ALL_ORDERS_COMMAND_ERROR, e);
        }
        request.setAttribute(JspParameterType.ORDERS, listResults);
        return PageType.ORDERS_LIST_PAGE.getAddress();
    }
}
