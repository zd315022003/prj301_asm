/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.admin;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Category;
import model.Product;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class AdminEditProductServlet extends HttpServlet {
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
        String sid = request.getParameter("id");
        if (sid == null) {
            response.sendRedirect("/admin/products");
            return;
        }
        int id = Integer.parseInt(sid);
        ProductDAO productDAO = new ProductDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        BrandDAO brandDAO = new BrandDAO();
        Product product = productDAO.getProductById(id);
        List<Category> categoriesByProduct = categoryDAO.getCategoriesByProduct(product);
        request.setAttribute("brands", brandDAO.getAll());
        request.setAttribute("categories", categoryDAO.getAll().stream()
                .filter(c -> categoriesByProduct.stream().map(Category::getCategory_id)
                        .noneMatch(integer -> integer == c.getId()))
                .collect(Collectors.toList()));
        request.setAttribute("product", product);
        request.setAttribute("productCategories", categoriesByProduct);
        brandDAO.closeConnection();
        categoryDAO.closeConnection();
        productDAO.closeConnection();
        request.getRequestDispatcher("/admin/edit-product.jsp").forward(request, response);
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
        String sid = request.getParameter("product-id");
        if (sid == null) {
            response.sendRedirect("products");
            return;
        }
        int id = Integer.parseInt(sid);
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String [] selectedCategories = request.getParameterValues("selected-categories");
        List<Integer> categories = Arrays.stream(selectedCategories == null? new String[0] : selectedCategories).map(Integer::parseInt).collect(Collectors.toList());
        String sPrice = request.getParameter("price");
        double price = Double.parseDouble(sPrice == null || sPrice.isEmpty() ? "0" : sPrice);
        String sBrand = request.getParameter("branding");
        int brand = Integer.parseInt(sBrand == null || sBrand.isEmpty() ? "0" : sBrand);
        String sSale = request.getParameter("sale");
        double sale = Double.parseDouble(sSale == null || sSale.isEmpty() ? "0" : sSale);
        String sQuantity = request.getParameter("quantity");
        int quantity = Integer.parseInt(sQuantity == null || sQuantity.isEmpty() ? "0" : sQuantity);
        String image = request.getParameter("thumbnail");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(id);
        if (product == null) {
            product = new Product();
            product.setProduct_id(-1);
        }
        product.setTitle(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setSale(sale);
        product.setQuantity(quantity);
        product.setThumbnail(image);
        product.setBranding_id(brand);
        productDAO.updateProduct(product);
        productDAO.updateProductCategories(product, categories);
        productDAO.closeConnection();
        response.sendRedirect("products");
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
