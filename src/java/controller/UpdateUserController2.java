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
import model.User;

/**
 *
 * @author Nam
 */
@WebServlet(name = "UpdateUserController2", urlPatterns = {"/updateUser2"})
public class UpdateUserController2 extends HttpServlet {

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
        User u = d.getUserById(id);
        if (u == null) {
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }
        if (u.isRole()) {
            request.setAttribute("role", 1);
        } else {
            request.setAttribute("role", 0);
        }

        request.setAttribute("userr", u);
        session.setAttribute("successRegist2", "Update user successfully!");
        request.getRequestDispatcher("UpdateUser2.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String acc = request.getParameter("acc");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("role");
        boolean role;
        if (role_raw.equals("0")) {
            role = false;
        } else {
            role = true;
        }
        User u = new User(id, role, name, acc, pass, email);
        d.updateUser1(u);
        response.sendRedirect("user");
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
