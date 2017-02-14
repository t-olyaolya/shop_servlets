package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
import com.company.Model.Bid;
import com.company.Model.Item;
import com.company.Model.User;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.company.Service.LangBundle.bundle;

/**
 * Created by tyuly on 09.02.2017.
 */
@WebServlet(name = "buy", urlPatterns = {"/buy"})
public class Shop extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("buy")) {
            DAO dao = new DAOImpl();
            int itemId = Integer.parseInt(request.getParameter("item.id"));
            User user = dao.getUser(Login.user.getName());
            Item item = dao.getItem(itemId);
            dao.createBid(user, item);
            request.setAttribute("info", bundle.getString("bought"));
            request.getRequestDispatcher("info_shop.jsp").forward(request, response);
        }
    }
}
