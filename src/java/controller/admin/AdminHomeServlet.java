/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.BrandDAO;
import dal.OrderDAO;
import dal.ProductDAO;
import dto.BrandDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public class AdminHomeServlet extends HttpServlet {
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
        OrderDAO orderDAO = new OrderDAO();
        BrandDAO brandDAO = new BrandDAO();
        ProductDAO productDAO = new ProductDAO();
        List<BrandDTO> brands = Optional.ofNullable(brandDAO.getAllBySale()).orElse(new ArrayList<>());
        request.setAttribute("brandNames", brands.stream().map(BrandDTO::getName).toArray());
        request.setAttribute("brandCounts", brands.stream().map(BrandDTO::getCount).toArray());
        request.setAttribute("productInfo", productDAO.getProductInfo());
        request.setAttribute("orders", orderDAO.getAll());
        brandDAO.closeConnection();
        orderDAO.closeConnection();
        request.getRequestDispatcher("admin.jsp").forward(request, response);
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
