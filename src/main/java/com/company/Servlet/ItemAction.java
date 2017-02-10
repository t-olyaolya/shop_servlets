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

/**
 * Created by tyuly on 10.02.2017.
 */
@WebServlet(name = "itemaction", urlPatterns = {"/itemaction"})
public class ItemAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (Items.isNew) { //create
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            try (PrintWriter out = response.getWriter()) {
                DAO dao = new DAOImpl();
                dao.createItem(name, Login.user, description);
                request.setAttribute("info", "Товар добавлен");
                request.getRequestDispatcher("info_shop.jsp").forward(request, response);
            }
            catch (HibernateException e) {
                e.printStackTrace();
            }

        } else { //edit
            String name = request.getParameter("name");
            String description = request.getParameter("description");
            try (PrintWriter out = response.getWriter()) {
                DAO dao = new DAOImpl();
                dao.editItem(Items.item, name, description);
                request.setAttribute("info", "Данные изменены");
                request.getRequestDispatcher("info_shop.jsp").forward(request, response);
            }
            catch (HibernateException e) {
                e.printStackTrace();
            }

        }

        //}
    }


}




