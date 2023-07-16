/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class AdminProductsServlet extends HttpServlet {
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
        ProductDAO productDAO = new ProductDAO();
        request.setAttribute("products", productDAO.getAll());
        request.getRequestDispatcher("/admin/products.jsp").forward(request, response);
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
        String editId = request.getParameter("edit");
        String deleteId = request.getParameter("delete");
        String action = request.getParameter("action");
        if (editId != null) {
            response.sendRedirect("edit-product?id=" + editId);
        } else if (deleteId != null) {
            ProductDAO productDAO = new ProductDAO();
            int id = Integer.parseInt(deleteId);
            productDAO.delete(id);
            productDAO.closeConnection();
            response.sendRedirect("products");
        } else if (action != null && action.equals("delete-all")) {
            String [] ids = request.getParameterValues("selected");
            List<Integer> idList = Arrays.stream(ids == null? new String[0] : ids).map(Integer::parseInt).collect(Collectors.toList());
            ProductDAO productDAO = new ProductDAO();
            productDAO.deleteAll(idList);
            productDAO.closeConnection();
            response.sendRedirect("products");
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
