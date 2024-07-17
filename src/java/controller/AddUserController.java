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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Nam
 */
@WebServlet(name = "AddUserServlet", urlPatterns = {"/addUser"})
public class AddUserController extends HttpServlet {

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
        String name = request.getParameter("name");
        String acc = request.getParameter("acc");
        String pass = request.getParameter("pass");
        String email = request.getParameter("email");
        String role_raw = request.getParameter("email");
        boolean role;
        if (role_raw.equals("0")) {
            role = false;
        } else {
            role = true;
        }
        if (!isValidUsername(name)) {
            session.setAttribute("errorRegist2", "Please enter correct user!");
            response.sendRedirect("AddUser.jsp");
            return;
        }
        if (d.getUserByAccount(acc) != null) {
            session.setAttribute("errorRegist2", "Account already exist!");
            response.sendRedirect("AddUser.jsp");
            return;
        }
        if (!isValidEmail(email)) {
            session.setAttribute("errorRegist2", "Syntax of the email is incorrect!");
            response.sendRedirect("AddUser.jsp");
            return;
        }
        if (d.getUserByEmail(email) != null) {
            session.setAttribute("errorRegist2", "Email is already used!");
            response.sendRedirect("AddUser.jsp");
            return;
        }
        session.setAttribute("successRegist2", "Add user successfully!");

        d.insertAUser(name, acc, pass, role, email);

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
        HttpSession session = request.getSession();
        //session.invalidate();
        DAO d = new DAO();
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String acc = request.getParameter("acc");
        String re_pass = request.getParameter("repass");
        String email = request.getParameter("email");

        if (!isValidUsername(name)) {
            session.setAttribute("errorRegist1", "You must enter correct user name!");
            response.sendRedirect("Register.jsp");
            return;
        }
        if (d.getUserByAccount(acc) != null) {
            session.setAttribute("errorRegist1", "Account already exist!");
            response.sendRedirect("Register.jsp");
            return;
        }

        if (!isValidEmail(email)) {
            session.setAttribute("errorRegist1", "Syntax of the email is incorrect!");
            response.sendRedirect("Register.jsp");
            return;
        }
        if (d.getUserByEmail(email) != null) {
            session.setAttribute("errorRegist1", "Email is already used!");
            response.sendRedirect("Register.jsp");
            return;
        }
        if (!pass.equals(re_pass)) {
            session.setAttribute("errorRegist1", "You must enter correct repassword!");
            response.sendRedirect("Register.jsp");
            return;
        }

        d.insertAUser(name, acc, pass, false, email);
        session.setAttribute("successRegist1", "Sign up successfully! Now log in to access.");
        response.sendRedirect("Login.jsp");
    }
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    private static boolean isValidEmail(String email) {
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public boolean isValidUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }
        char firstChar = username.charAt(0);
        if (!Character.isLetter(firstChar)) {
            return false;
        }
        for (int i = 1; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        if (username.length() < 2 || username.length() > 20) {
            return false;
        }
        return true;
    }

}
