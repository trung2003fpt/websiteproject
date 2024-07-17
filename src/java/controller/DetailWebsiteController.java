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
import model.Comment;
import model.User;

/**
 *
 * @author Nam
 */
@WebServlet(name = "DetailController", urlPatterns = {"/detail"})
public class DetailWebsiteController extends HttpServlet {

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
            int aid = Integer.parseInt(request.getParameter("aid"));
            DAO d = new DAO();
            Article a = d.getArticleById(aid);
            Category c = d.getCategoryById(a.getCategory().getId());
            List<Category> categories = d.getAllCategory();
            List<Article> articles = d.getAllArticle();
            List<Article> categorizedArticles = d.getAllArticleByCid(a.getCategory().getId());
            List<Comment> comments = d.getAllCommentbyAid(aid);
        
            request.setAttribute("articles", articles);
            request.setAttribute("cate", c);
            request.setAttribute("cates", categories);
            request.setAttribute("arti", a);
            request.setAttribute("categorizedArticles",categorizedArticles);
            request.setAttribute("coms", comments);

            request.getRequestDispatcher("DetailWebsite.jsp").forward(request, response);
        } catch (NullPointerException | NumberFormatException e ) {
            request.getRequestDispatcher("error").forward(request, response);
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
        DAO d = new DAO();
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            request.getRequestDispatcher("TryWeb.jsp").forward(request, response);
            return;
        }
        String contentCom = request.getParameter("contentCom");
        String aid_raw = request.getParameter("aid");
        int aid = Integer.parseInt(aid_raw);

        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        User u = (User) session.getAttribute("user");
        d.insertAnComment(contentCom, String.valueOf(date), aid, u.getId());
        response.sendRedirect("detail?aid=" + aid_raw);
    }
}
