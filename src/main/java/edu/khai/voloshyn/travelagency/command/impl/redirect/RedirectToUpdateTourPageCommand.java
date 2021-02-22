package edu.khai.voloshyn.travelagency.command.impl.redirect;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.util.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectToUpdateTourPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new CommandUtil().setTourSessionAttribute(request);
        return PageType.UPDATE_TOUR_PAGE.getAddress();
    }
}
