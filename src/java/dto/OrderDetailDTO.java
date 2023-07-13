package dto;

public class OrderDetailDTO {
    private int orderId;
    private CartItemDTO cartItem;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(int orderId, CartItemDTO cartItem) {
        this.orderId = orderId;
        this.cartItem = cartItem;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CartItemDTO getCartItem() {
        return cartItem;
    }

    public void setCartItem(CartItemDTO cartItem) {
        this.cartItem = cartItem;
    }
}
