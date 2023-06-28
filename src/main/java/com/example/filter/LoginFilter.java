package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/upload.jsp", "/result.jsp", "/data.jsp", "/colResult.jsp", "/history.jsp", "/parseError.jsp", "/utilityError.jsp"})
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        HttpSession session = req.getSession();
        String requestUrl = req.getRequestURI();
        if (requestUrl.endsWith("/login") || requestUrl.endsWith("/login.jsp") || requestUrl.equals("/")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else if (session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
