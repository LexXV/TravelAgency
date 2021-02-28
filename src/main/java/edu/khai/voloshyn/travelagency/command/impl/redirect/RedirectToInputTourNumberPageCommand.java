package edu.khai.voloshyn.travelagency.command.impl.redirect;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.PageType;
import edu.khai.voloshyn.travelagency.command.util.CommandUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Redirect To Input Tour Number Page Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class RedirectToInputTourNumberPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        new CommandUtil().setTourSessionAttribute(request);
        return PageType.INPUT_TOUR_NUMBER_PAGE.getAddress();
    }
}
