/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dto.BrandDTO;
import dto.CategoryDTO;
import dto.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class ShopServlet extends HttpServlet {
   
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
            out.println("<title>Servlet ShopServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ShopServlet at " + request.getContextPath () + "</h1>");
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
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        ProductDAO productDAO = new ProductDAO();
        
        List<CategoryDTO> categories = categoryDAO.getAll();
        List<BrandDTO> brands = brandDAO.getAll();
        
        String search = request.getParameter("search");
        String[] categoriesParam = request.getParameterValues("categories");
        String[] brandsParam = request.getParameterValues("brands");
        search = search == null ? "" : search;
        List<Integer> selectedCategories = categoriesParam == null
                ? new ArrayList<>()
                : Arrays.stream(categoriesParam)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        List<Integer> selectedBrands = brandsParam == null
                ? new ArrayList<>()
                : Arrays.stream(brandsParam)
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
        
        List<ProductDTO> products = productDAO.getProductByFilter(search, selectedCategories, selectedBrands);
        System.out.println(products.size());
        
        productDAO.closeConnection();
        brandDAO.closeConnection();
        categoryDAO.closeConnection();
        
        request.setAttribute("categories", categories);
        request.setAttribute("brands", brands);
        request.setAttribute("products", products);
        request.setAttribute("selectedCategories", selectedCategories);
        request.setAttribute("selectedBrands", selectedBrands);
        request.setAttribute("search", search);
        request.getRequestDispatcher("shop.jsp").forward(request, response);
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
        processRequest(request, response);
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
