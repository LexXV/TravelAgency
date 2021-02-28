package edu.khai.voloshyn.travelagency.filter;


import edu.khai.voloshyn.travelagency.command.constants.SessionAttribute;
import edu.khai.voloshyn.travelagency.entity.Role;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The type Role security filter.
 *
 * @author Voloshyn Oleksii
 * @version 1.0
 */
public class RoleFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession httpSession = httpServletRequest.getSession();
        Object userRoleObject = httpSession.getAttribute(SessionAttribute.ROLE);
        if (userRoleObject == null) {
            httpSession.setAttribute(SessionAttribute.ROLE, Role.GUEST);
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
