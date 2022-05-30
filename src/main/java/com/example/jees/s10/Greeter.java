package com.example.jees.s10;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s10/greeter")
public class Greeter extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Greeter.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.trace("called");

        String user = request.getParameter("user");

        HttpSession session = request.getSession();
        String prevUser = (String) session.getAttribute("user");

        if (user == null) {
            session.invalidate();
        } else if (user.isBlank()) {
            session.setAttribute("user", "unknown");
        } else {
            session.setAttribute("user", user);
        }

        request.setAttribute("previous", prevUser);
        request.getRequestDispatcher("greeter.jsp").forward(request, response);
    }
}
