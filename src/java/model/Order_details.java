/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Order_details {
    private int order_id;
    private int product_it;
    private int price;
    private int num;

    public Order_details() {
    }

    public Order_details(int order_id, int product_it, int price, int num) {
        this.order_id = order_id;
        this.product_it = product_it;
        this.price = price;
        this.num = num;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_it() {
        return product_it;
    }

    public void setProduct_it(int product_it) {
        this.product_it = product_it;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    
}
