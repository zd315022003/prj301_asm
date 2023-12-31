/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.ProductDAO;
import dto.CartItemDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;
import ulti.CookieUltis;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public class ShoppingCartServlet extends HttpServlet {
   
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
        List<CartItemDTO> cartItems = CookieUltis.parseCartItems(request);
        request.setAttribute("cartItems", cartItems);
        request.getRequestDispatcher("shopping-cart.jsp").forward(request, response);

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
        // Add or remove cookie with pattern: cart = {productID1_quantity1}_{productID2_quantity2},...
        String productID = request.getParameter("productID");
        String quantity = request.getParameter("quantity");
        quantity = quantity == null? "1" : quantity;
        String action = request.getParameter("action");
        String continueUrl = request.getParameter("continueUrl");

        Cookie cart = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("cart"))
                .findFirst().orElse(new Cookie("cart", ""));

        if (action != null && action.equals("add")) {
            try {
                ProductDAO productDAO = new ProductDAO();
                Product productById = productDAO.getProductById(Integer.parseInt(productID));
                if (productById.getQuantity() < Integer.parseInt(quantity)) {
                    throw new Exception();
                }
                CookieUltis.addToCart(cart, productID, quantity);
            } catch (Exception e) {
                response.sendRedirect(continueUrl + "&error=true");
                return;
            }
        } else {
            CookieUltis.removeFromCart(cart, productID);
        }

        cart.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(cart);
        response.sendRedirect("shopping-cart");
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
