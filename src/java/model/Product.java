/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Product {

    private int product_id;
    private String title;
    private double price;
    private double sale;
    private String thumbnail;
    private String description;
    private int quantity;
    private int brading_id;
    private int filter_price_id;

    public Product() {
    }

    public Product(int product_id, String title, double price, double sale, String thumbnail, String description, int quantity, int brading_id, int filter_price_id) {
        this.product_id = product_id;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.brading_id = brading_id;
        this.filter_price_id = filter_price_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getBrading_id() {
        return brading_id;
    }

    public void setBrading_id(int brading_id) {
        this.brading_id = brading_id;
    }

    public int getFilter_price_id() {
        return filter_price_id;
    }

    public void setFilter_price_id(int filter_price_id) {
        this.filter_price_id = filter_price_id;
    }

    
}
