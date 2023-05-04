package com.company.servlet;

import jdbc.controller.UserController;
import jdbc.model.User;

import java.io.*;
import java.sql.SQLException;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    private final UserController userController = new UserController();
    private final Random random = new Random();

    public void init() {
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String id = request.getParameter("id");

        User user = null;
        try {
            user = userController.getUserById(Integer.valueOf(id));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.println(user);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String age = request.getParameter("age");

        if (name.equals("") || surname.equals("")) {
            response.sendRedirect(request.getContextPath() + "/error.jsp");
            return;
        }

        int id = random.nextInt(1000);

        try {
            userController.addUser(id, name, surname, Integer.parseInt(age));
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.println("User has added successfully");
    }

    public void destroy() {
    }
}