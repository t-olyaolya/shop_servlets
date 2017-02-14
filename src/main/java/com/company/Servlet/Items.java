package com.company.Servlet;

import com.company.DAO.DAO;
import com.company.DAO.DAOImpl;
import com.company.Model.Item;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.company.Service.LangBundle.bundle;

/**
 * Created by tyuly on 10.02.2017.
 */
@WebServlet (name = "myitems", urlPatterns = {"/myitems"})
public class Items extends HttpServlet {
    static Item item;
    static boolean isNew = true;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("action").equals("delete")) {
            itemDlt(request,response);
        }
        if (request.getParameter("action").equals("edit")) {
            isNew = false;
            itemEdit(request,response);
        }
        if (request.getParameter("action").equals("add")) {
            isNew = true;
            request.getRequestDispatcher("editItem.jsp").forward(request, response);

        }
    }

    public void itemEdit(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException{
        DAO dao = new DAOImpl();
        int itemId = Integer.parseInt(request.getParameter("item.id"));
        item = dao.getItem(itemId);
        request.getRequestDispatcher("editItem.jsp").forward(request, response);
    }

    public void itemDlt(HttpServletRequest request, HttpServletResponse response)  throws IOException,ServletException{
        DAO dao = new DAOImpl();
        int itemId = Integer.parseInt(request.getParameter("item.id"));
        Item item = dao.getItem(itemId);
        dao.dltItem(item);
        request.setAttribute("info",  bundle.getString("iDel"));
        request.getRequestDispatcher("info_shop.jsp").forward(request, response);
    }
}

