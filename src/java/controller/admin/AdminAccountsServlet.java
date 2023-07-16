/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.AccountDAO;
import dto.AccountDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Account;

import java.io.IOException;

/**
 * @author Admin
 */
public class AdminAccountsServlet extends HttpServlet {
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAO();
        request.setAttribute("accounts", accountDAO.getAll());
        String selectedId = request.getParameter("selected");
        int currentId = accountDAO.getCurrentId(((AccountDTO) request.getSession().getAttribute("account")).getUsername());
        request.setAttribute("selected", selectedId == null ? currentId : Integer.parseInt(selectedId));
        request.getRequestDispatcher("accounts.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String updateId = request.getParameter("update");
        String deleteId = request.getParameter("disable-enable");
        String makeAdminId = request.getParameter("make-admin");
        if (deleteId != null) {
            int id = Integer.parseInt(deleteId);
            String status = request.getParameter("status");
            AccountDAO accountDAO = new AccountDAO();
            accountDAO.delete(id, status.equals("1")? 0 : 1);
            accountDAO.closeConnection();
            response.sendRedirect("accounts?selected=" + id);
        } else if (makeAdminId != null) {
            int id = Integer.parseInt(makeAdminId);
            AccountDAO accountDAO = new AccountDAO();
            String roleId = request.getParameter("role");
            accountDAO.makeAdmin(id, roleId.equals("1")? 0 : 1);
            accountDAO.closeConnection();
            response.sendRedirect("accounts?selected=" + id);
        } else {
            int id = Integer.parseInt(updateId);
            String username = request.getParameter("username");
            String firstName = request.getParameter("first-name");
            String lastName = request.getParameter("last-name");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            AccountDAO accountDAO = new AccountDAO();
            Account account = accountDAO.getById(id);
            if (username == null || username.isEmpty()) {
                username = account.getUsername();
            }
            if (account != null) {
                account.setUsername(username);
                account.setFirst_name(firstName);
                account.setLast_name(lastName);
                account.setEmail(email);
                account.setPhone_number(phone);
                accountDAO.update(account);
                accountDAO.closeConnection();
            }
            response.sendRedirect("accounts?selected=" + id);
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
