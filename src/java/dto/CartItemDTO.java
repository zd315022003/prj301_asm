package dto;

public class CartItemDTO {
    private int productID;
    private int quantity;

    private String productName;
    private String productImage;
    private double productPrice;

    public CartItemDTO() {
    }

    public CartItemDTO(int productID, int quantity, String productName, String productImage, double productPrice) {
        this.productID = productID;
        this.quantity = quantity;
        this.productName = productName;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
