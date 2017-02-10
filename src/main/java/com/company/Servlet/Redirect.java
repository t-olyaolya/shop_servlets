package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
import com.company.Model.Item;
import com.company.Model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by tyuly on 09.02.2017.
 */
@WebServlet(name = "redirect", urlPatterns = {"/redirect"})
public class Redirect extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("redirect")) {
            DAO dao = new DAOImpl();
            dao.openTransaction();
            List<Item> list = dao.getItemsForBuy(Login.user);
            request.setAttribute("user",Login.user.getName());
            request.setAttribute("list", list);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
        }
    }


}