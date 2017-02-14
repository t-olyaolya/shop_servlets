package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
import com.company.Model.Bid;
import com.company.Model.Item;
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

import static com.company.Service.LangBundle.bundle;

/**
 * Created by tyuly on 10.02.2017.
 */
@WebServlet(name = "account", urlPatterns = {"/account"})
public class Account extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (request.getParameter("action").equals("mybids")) {
            myBids(request,response);
        }
        if (request.getParameter("action").equals("myitems")) {
            myItems(request,response);
        }
        if (request.getParameter("action").equals("allitems")) {
            allItems(request,response);
        }
        if (request.getParameter("action").equals("edituser")) {
            editUser(request,response);
        }
        if (request.getParameter("action").equals("dltuser")) {
            dltUser(request,response);
        }
        if (request.getParameter("action").equals("logout")) {
            logout(request,response);
        }
    }

    public void myBids(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        DAO dao = new DAOImpl();
        List<Bid> list = dao.getBidsUser(Login.user);
        List<Item> item = new ArrayList<>();
        for (Bid bid : list) {
            item.add(bid.getItem());
        }

        request.setAttribute("user", Login.user.getName());
        request.setAttribute("list", item);
        request.getRequestDispatcher("userBids.jsp").forward(request, response);
    }

    public void myItems(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        DAO dao = new DAOImpl();
        List<Item> list = dao.getUser(Login.user.getName()).getItemList();
        request.setAttribute("user", Login.user.getName());
        request.setAttribute("list", list);
        request.getRequestDispatcher("myItems.jsp").forward(request, response);
    }

    public void allItems(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        DAO dao = new DAOImpl();
        dao.openTransaction();
        List<Item> list = dao.getItems();
        request.setAttribute("user", Login.user.getName());
        request.setAttribute("list", list);
        request.getRequestDispatcher("allItems.jsp").forward(request, response);
    }

    public void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        request.getRequestDispatcher("editUser.jsp").forward(request, response);
    }

    public void dltUser(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        DAO dao = new DAOImpl();
        dao.dltUser(Login.user);
        Login.user = null;
        request.setAttribute("info",bundle.getString("dlltUser"));
        request.getRequestDispatcher("info.jsp").forward(request, response);
        dao.closeSession();

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {
        DAO dao = new DAOImpl();
        Login.user = null;
        request.setAttribute("info", bundle.getString("out"));
        request.getRequestDispatcher("info.jsp").forward(request, response);
        dao.closeSession();

    }

}
