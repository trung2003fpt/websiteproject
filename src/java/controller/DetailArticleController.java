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
import model.Comment;
import model.User;

/**
 *
 * @author Nam
 */
@WebServlet(name = "DetailArticleController", urlPatterns = {"/detailArticle"})
public class DetailArticleController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        DAO d = new DAO();
        Article a = d.getArticleById(id);

        HttpSession session = request.getSession();
        try {
            if (a == null) {
                request.getRequestDispatcher("Error.jsp").forward(request, response);
            }
            request.setAttribute("article", a);
            request.setAttribute("cates", d.getAllCategory());
            request.setAttribute("writer", d.getAllWriter());
            request.getRequestDispatcher("ArticleDetail.jsp").forward(request, response);
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
