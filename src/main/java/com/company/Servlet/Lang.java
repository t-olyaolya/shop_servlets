package com.company.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.company.Service.LangBundle.locale;
import static com.company.Service.LangBundle.bundle;

/**
 * Created by tyuly on 14.02.2017.
 */
@WebServlet(name = "lang", urlPatterns = {"/lang"})
public class Lang extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action").equals("ru")) {
            if (!locale.equals("ru")) {
                locale = new Locale("ru");
                bundle = ResourceBundle.getBundle("message", locale);
                MainPage.welcomePage(request,response);
                //request.getRequestDispatcher("welcome.jsp");

            }
        }
        if (request.getParameter("action").equals("en")) {
            if (!locale.equals("en")) {
                locale = new Locale("en");
                bundle = ResourceBundle.getBundle("message", locale);
                MainPage.welcomePage(request,response);
                //request.getRequestDispatcher("welcome.jsp");
            }
        }
    }
}
