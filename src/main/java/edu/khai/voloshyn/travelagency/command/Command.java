package edu.khai.voloshyn.travelagency.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
