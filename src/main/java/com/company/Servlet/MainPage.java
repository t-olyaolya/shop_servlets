package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
import com.company.Model.Item;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by tyuly on 10.02.2017.
 */
@WebServlet(name = "/", urlPatterns = {"/"})
public class MainPage extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        DAO dao = new DAOImpl();
        if (Login.user!=null) {
            dao.openTransaction();
            List<Item> list = dao.getItemsForBuy(Login.user);
            request.setAttribute("user",Login.user.getName());
            request.setAttribute("list", list);
            request.getRequestDispatcher("shop.jsp").forward(request, response);
            } else {
            dao.openTransaction();
            List<Item> list = dao.getItemsForBuy(null);
            request.setAttribute("info", "Вы не вошли");
            request.setAttribute("list", list);
            request.getRequestDispatcher("welcome.jsp").forward(request, response);
        }
    }
}
