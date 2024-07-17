/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;
import model.Article;
import model.Category;
import model.User;

/**
 *
 * @author Nam
 */
@WebServlet(name = "AddArticleController", urlPatterns = {"/addArticle"})
public class AddArticleController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO d = new DAO();
        List<Category> cates = d.getAllCategory();
        long mili = System.currentTimeMillis();
        Date date = new Date(mili);
        request.setAttribute("date", date);
        request.setAttribute("cates", cates);
        request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            DAO d = new DAO();
            HttpSession session = request.getSession();
            String title = request.getParameter("title");
            String content = request.getParameter("content");
            String image = request.getParameter("image");
            String postDate_raw = request.getParameter("postDate");
//            if (date.compareTo(Date.valueOf(postDate_raw)) < 0) {
//                session.setAttribute("errorArticle", "Post date must smaller than the current date");
//                request.setAttribute("title", title);
//                request.setAttribute("content", content);
//                request.setAttribute("image", image);
//                List<Category> cates = d.getAllCategory();
//                request.setAttribute("cates", cates);
//                request.getRequestDispatcher("AddArticle.jsp").forward(request, response);
//
//                return;
//            }

            User u = (User) session.getAttribute("user");
            int userID = u.getId();
            int cateID = Integer.parseInt(request.getParameter("cateID"));
            d.insertAnArticle(title, content, image, postDate_raw, userID, cateID);
            Article a = d.getArticleJustAdd();
            d.insertViewForNewArticle(a.getId());
            session.setAttribute("successAddarticle", "Add article successfully!");

            response.sendRedirect("article");
        } catch (NullPointerException | NumberFormatException e) {
            response.sendRedirect("Error.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
