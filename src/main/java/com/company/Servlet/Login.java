package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
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
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tyuly on 08.02.2017.
 */
@WebServlet(name = "log", urlPatterns = {"/log"})
public class Login extends HttpServlet {
    static User user = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAOImpl();
            if (dao.auth(name,password)) {
                user = dao.getUser(name);
                List<Item> list = dao.getItemsForBuy(user);
                request.setAttribute("user",user.getName());
                request.setAttribute("list", list);
                request.getRequestDispatcher("shop.jsp").forward(request, response);

            } else {
                request.setAttribute("info", "Неверный пароль");
                request.getRequestDispatcher("info.jsp").forward(request, response);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


}

