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
import java.util.List;
import model.Article;
import model.Category;
import model.User;

/**
 *
 * @author Nam
 */
@WebServlet(name = "ArticleController", urlPatterns = {"/article"})
public class ArticleController extends HttpServlet {

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
        HttpSession session = request.getSession();
        try {
            User u = (User) session.getAttribute("user");
            if (!u.isRole()) {
                response.sendRedirect("TryWeb2.jsp");
                return;
            }
            DAO d = new DAO();

            //List<Article> arti = d.getAllArticleByUid(u.getId());
            List<Article> arti = d.getAllArticle();
            List<Article> arti2 = d.getAllArticleByUid(u.getId());
            
            request.setAttribute("articles", arti);
            request.setAttribute("articles2", arti2);
            
            List<Category> cates = d.getAllCategory();
            request.setAttribute("cates", cates);
            request.getRequestDispatcher("ListArticle.jsp").forward(request, response);
        } catch (NullPointerException | NumberFormatException e) {
            response.sendRedirect("TryWeb2.jsp");
        }
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

    }

}
