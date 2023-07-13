/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dal.OrderDAO;
import dal.OrderDetailDAO;
import dal.ProfileDAO;
import dto.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ulti.CookieUltis;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Admin
 */
public class CheckoutServlet extends HttpServlet {
   
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
            out.println("<title>Servlet HomeServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet HomeServlet at " + request.getContextPath () + "</h1>");
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
        AccountDTO account = (AccountDTO) request.getSession().getAttribute("account");
        String username = Optional.ofNullable(account).map(AccountDTO::getUsername).orElse("default");
        ProfileDAO profileDAO = new ProfileDAO();
        ProfileDTO profile = profileDAO.getprofile(username);

        List<CartItemDTO> cartItems = CookieUltis.parseCartItems(request);

        profileDAO.closeConnection();
        request.setAttribute("profile", profile);
        request.setAttribute("cartItems", cartItems);
        request.setAttribute("total", cartItems.stream().mapToDouble(CartItemDTO::getTotal).sum());
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
        // create order and order detail, data get from request
        String firstName= request.getParameter("first-name");
        String lastName= request.getParameter("last-name");
        String address= request.getParameter("address");
        String phone= request.getParameter("phone-number");
        String email= request.getParameter("email");
        String note= request.getParameter("note");

        AccountDTO account = (AccountDTO) request.getSession().getAttribute("account");
        String username = Optional.ofNullable(account).map(AccountDTO::getUsername).orElse("default");
        ProfileDAO profileDAO = new ProfileDAO();
        int profileId = profileDAO.getprofileId(username);
        profileDAO.closeConnection();

        OrderDTO orderDTO = new OrderDTO(firstName, lastName, address, phone, email, note, profileId);
        OrderDAO orderDAO = new OrderDAO();
        int orderId = orderDAO.createOrder(orderDTO);

        List<CartItemDTO> cartItems = CookieUltis.parseCartItems(request);
        List<OrderDetailDTO> orderDetailDTOs = new ArrayList<>();
        for (CartItemDTO cartItem : cartItems) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderId, cartItem);
            orderDetailDTOs.add(orderDetailDTO);
        }
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        orderDetailDAO.createOrderDetails(orderDetailDTOs);
        request.setAttribute("orderId", orderId);
        Cookie cart = Arrays.stream(request.getCookies()).map(Cookie::getName).filter(name -> name.equals("cart")).map(name -> new Cookie(name, "")).findFirst().orElse(new Cookie("cart", ""));
        cart.setMaxAge(0);
        response.addCookie(cart);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
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
