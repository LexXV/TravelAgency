package edu.khai.voloshyn.travelagency.command.impl.user;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.command.util.CommandUtil;
import edu.khai.voloshyn.travelagency.entity.Role;
import edu.khai.voloshyn.travelagency.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignOutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession().setAttribute(SessionAttribute.ROLE, Role.GUEST);
        request.getSession().setAttribute(SessionAttribute.USER, new User());
        new CommandUtil().initializeTourParameters(request);
        return PageType.HOME_PAGE.getAddress();
    }
}
