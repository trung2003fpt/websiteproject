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

/**
 *
 * @author Nam
 */
@WebServlet(name = "DeleteUserController", urlPatterns = {"/deleteUser"})
public class DeleteUserController extends HttpServlet {

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
        List<Comment> coms = d.getAllCommentbyUid(id);
        List<Article> artis = d.getAllArticleByUid(id);
        for (Comment com : coms) {
            d.deleteAComment(com.getId());
        }
        for (Article arti : artis) {
            List<Comment> coms1 = d.getAllCommentbyAid(arti.getId());
            for (Comment comment : coms1) {
                d.deleteAComment(comment.getId());
            }
            d.deleteDem(arti.getId());
            d.deleteAnArticle(arti.getId());
        }
        d.deleteAUser(id);
//        List<Article> artis = d.getAllArticle();
//        request.setAttribute("articles", artis);
//        request.getRequestDispatcher("ListArticle.jsp").forward(request, response);
        session.setAttribute("successRegist2", "Delete user successfully!");
        response.sendRedirect("user");
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
