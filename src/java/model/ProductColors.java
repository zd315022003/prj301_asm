/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class ProductColors {
    private int ProductID;
    private int ColorsID;
    private int quantity;

    public ProductColors() {
    }

    public ProductColors(int ProductID, int ColorsID, int quantity) {
        this.ProductID = ProductID;
        this.ColorsID = ColorsID;
        this.quantity = quantity;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getColorsID() {
        return ColorsID;
    }

    public void setColorsID(int ColorsID) {
        this.ColorsID = ColorsID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
