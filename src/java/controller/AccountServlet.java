/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProfileDAO;
import dto.AccountDTO;
import dto.ProfileDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;
import ulti.MD5Hash;

/**
 *
 * @author Admin
 */
public class AccountServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AccountServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AccountServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        AccountDTO account = (AccountDTO) session.getAttribute("account");
        ProfileDAO profileDAO = new ProfileDAO();
        ProfileDTO profile = profileDAO.getprofile(account.getUsername());
        request.setAttribute("profile", profile);
        request.getRequestDispatcher("profile.jsp").forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String img_url = request.getParameter("img_url");
            String username = request.getParameter("pusername");
            String firstName = request.getParameter("pfname");
            String lastName = request.getParameter("plname");
            String email = request.getParameter("pemail");
            String currentpass = request.getParameter("currentpass");
            String newpass = request.getParameter("newpass");
            ProfileDAO pd = new ProfileDAO();
            if(pd.checkPassword(username, MD5Hash.hash(currentpass)) || (newpass != null && newpass.isEmpty())){
                    ProfileDTO pdto = new ProfileDTO( img_url, username, firstName, lastName, email, MD5Hash.hash(currentpass), MD5Hash.hash(newpass));
                    pd.change(pdto);
            }else{
                request.setAttribute("errorMessage", "Existed account!");
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("profile");
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
