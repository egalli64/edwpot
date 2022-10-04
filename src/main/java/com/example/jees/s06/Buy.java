package com.example.jees.s06;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("serial")
@WebServlet("/s06/buy")
public class Buy extends HttpServlet {
    private static final Logger log = LogManager.getLogger(Buy.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        log.traceEntry();

        HttpSession session = request.getSession(false);
        if (session != null) {
            @SuppressWarnings("unchecked")
            Map<String, Album> orders = (Map<String, Album>) session.getAttribute("orders");
            if (orders != null) {
                request.setAttribute("albums", orders.values());
            }
            session.invalidate();
        }

        request.getRequestDispatcher("done.jsp").forward(request, response);
    }
}
