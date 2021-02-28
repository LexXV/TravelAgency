package edu.khai.voloshyn.travelagency.command.impl.redirect;

import edu.khai.voloshyn.travelagency.command.Command;
import edu.khai.voloshyn.travelagency.command.constants.JspParameterType;
import edu.khai.voloshyn.travelagency.command.constants.PageType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The type Redirect Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class RedirectCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PageType.valueOf(request.getParameter(JspParameterType.ADDRESS)).getAddress();
    }
}