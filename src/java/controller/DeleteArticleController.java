/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Article;
import model.Category;
import model.Comment;

/**
 *
 * @author Nam
 */
@WebServlet(name = "DeleteArticleController", urlPatterns = {"/deleteArticle"})
public class DeleteArticleController extends HttpServlet {

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
        int id = Integer.parseInt(request.getParameter("id"));
        DAO d = new DAO();
        List<Comment> coms = d.getAllCommentbyAid(id);
        for (Comment com : coms) {
            d.deleteAComment(com.getId());
        }
        d.deleteDem(id);
        d.deleteAnArticle(id);
//        List<Article> artis = d.getAllArticle();
//        request.setAttribute("articles", artis);
//        request.getRequestDispatcher("ListArticle.jsp").forward(request, response);
        session.setAttribute("successAddarticle", "Delete article successfully!");
        response.sendRedirect("article");
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
