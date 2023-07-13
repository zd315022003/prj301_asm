package ulti;

import dal.ProductDAO;
import dto.CartItemDTO;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieUltis {
    public static List<CartItemDTO> parseCartItems(HttpServletRequest request) {
        Cookie cart = Arrays.stream(request.getCookies())
                .filter(cookie -> cookie.getName().equals("cart"))
                .findFirst().orElse(new Cookie("cart", ""));
        String cartValue = cart.getValue();
        Map<Integer, Integer> rawCartItems = new HashMap<>();
        while (cartValue.contains("{")) {
            System.out.println(cartValue);
            int productID = Integer.parseInt(
                    cartValue.substring(
                            cartValue.indexOf("{") + 1
                            , cartValue.indexOf("_")));
            int quantity = Integer.parseInt(
                    cartValue.substring(
                            cartValue.indexOf("_") + 1
                            , cartValue.indexOf("}")));
            rawCartItems.put(productID, quantity);
            String regex = "\\{" + productID + "_\\d+}_*";
            cartValue = cartValue.replaceAll(regex, "");
        }
        ProductDAO productDAO = new ProductDAO();
        List<CartItemDTO> results = productDAO.getCartItems(rawCartItems.keySet());
        results.forEach(item -> item.setQuantity(rawCartItems.get(item.getProductID())));
        productDAO.closeConnection();
        return results;
    }

    public static void removeFromCart(Cookie cart, String productID) {
        String cartValue = cart.getValue();
        String regex = "\\{" + productID + "_\\d+}_*";
        cartValue = cartValue.replaceAll(regex, "");
        cart.setValue(cartValue);
    }

    public static void addToCart(Cookie cart, String productID, String quantity) {
        removeFromCart(cart, productID);
        String cartValue = cart.getValue();
        cartValue += cartValue.isEmpty()
                ? "{" + productID + "_" + quantity + "}"
                : "_{" + productID + "_" + quantity + "}";
        cart.setValue(cartValue);
    }

}
