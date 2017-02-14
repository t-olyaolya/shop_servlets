package com.company.Servlet;

import com.company.DAO.*;
import com.company.Model.User;
import org.hibernate.HibernateException;
import org.json.JSONObject;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.company.Service.LangBundle.bundle;


@WebServlet (name = "reg", urlPatterns = {"/reg"})
public class Registry extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String name = request.getParameter("name").toLowerCase();
        String password = request.getParameter("password");
        try (PrintWriter out = response.getWriter()) {
            DAO dao = new DAOImpl();
            if (!dao.checkName(name,password)) {
                dao.createUser(name, password);
                dao.closeSession();
                request.setAttribute("info",  bundle.getString("completed"));
                request.getRequestDispatcher("info.jsp").forward(request, response);

            } else {
                request.setAttribute("info",  bundle.getString("already"));
                request.getRequestDispatcher("info.jsp").forward(request, response);
            }
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }


}
