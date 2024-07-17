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
import model.User;
import model.dem;

/**
 *
 * @author Nam
 */
@WebServlet(name = "HomeController", urlPatterns = {"/home"})
public class HomeController extends HttpServlet {

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
        DAO d = new DAO();
        List<Category> cates = d.getAllCategory();
        String cid = request.getParameter("cid");
        String find = request.getParameter("find");
        session.setAttribute("cid", cid);
        int count = 0;
        session.setAttribute("view", count);
        try {
            if (cid != null) {
                int cid_num = Integer.parseInt(cid);
                if (cid_num <= 0 || cid_num > cates.size()) {
                    throw new IndexOutOfBoundsException();
                }
            }
            List<dem> dems = d.getAlldem();
            request.setAttribute("dem", dems);
            if (find == null) {
                if (cid == null) {
                    // lay tat ca bai viet
                    List<Article> articles = d.getAllArticle();
                    // lay danh sach bai viet theo ten cua the loai
                    List<Article> latestArticles = d.getAllArticleSortByCname();
                    // lay danh sach bai viet theo thoi gian
                    List<Article> articles1 = d.getAllLatestArticleByCid();
                    // lay bai viet thei view
                    List<Article> articles2 = d.getMostViewArticle();

                    request.setAttribute("articles", articles2);
                } else {
                    // lay tin theo thu tu ten cua the loai
                    List<Article> categorizedArticles = d.getAllArticleByCid(Integer.parseInt(cid));
                    // lay tin theo so luong view cua bai viet
                    List<Article> categorizedArticles1 = d.getMostViewArticleByCid(Integer.parseInt(cid));
                    request.setAttribute("articles", categorizedArticles1);
                }
                request.setAttribute("cid", cid);
                request.setAttribute("cates", cates);
                request.getRequestDispatcher("HomeWebsite.jsp").forward(request, response);
            }

            List<Article> artis = d.searchArticle(find);
            if (artis.isEmpty()) {
                request.setAttribute("none", "There is no result.");
            } else {
                request.setAttribute("articles", artis);
            }
            request.setAttribute("cates", cates);
            request.getRequestDispatcher("HomeWebsite.jsp").forward(request, response);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            response.sendRedirect("Error.jsp");
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
        String acc = request.getParameter("name");
        String pass = request.getParameter("pass");
        DAO d = new DAO();
        List<Category> cates = d.getAllCategory();
        request.setAttribute("cates", cates);
        boolean checked = d.checkUser(acc, pass);
        User u = d.getUserByAccount(acc);

        if (checked) {
            request.setAttribute("user", u);
            request.setAttribute("acc", acc);
            request.getRequestDispatcher("HomeWebsite.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }

}
