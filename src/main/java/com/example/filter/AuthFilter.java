package com.example.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/user/*")
public class AuthFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        if(Objects.nonNull(httpReq)){
            HttpSession session = httpReq.getSession();
            if(!Objects.nonNull(session.getAttribute("user"))) {
                httpRes.sendRedirect("/login.jsp");
            }
            chain.doFilter(request,response);

        }
    }


    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}