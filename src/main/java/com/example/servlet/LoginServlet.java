package com.example.servlet;

import com.example.Users;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    Logger logger = Logger.getLogger(LoginServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
        HttpSession session = req.getSession();
        if(Objects.nonNull(session.getAttribute("user"))){
            resp.sendRedirect("/user/hello.jsp");
        }
        resp.sendRedirect("/login.jsp");}
        catch (Exception e){
            logger.severe(e.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            HttpSession session = req.getSession();
            if (Objects.nonNull(req.getAttribute("login"))
                    && Objects.nonNull(req.getAttribute("password"))) {
                if (Users.getInstance().getUsers().indexOf(req.getAttribute("login")) != -1) {
                    session.setAttribute("user", req.getAttribute("login"));
                    resp.sendRedirect("/user/hello.jsp");
                } else {
                    resp.sendRedirect("/login.jsp");
                }
            }
        }catch (Exception e){
            logger.severe(e.getMessage());
        }
    }
}
