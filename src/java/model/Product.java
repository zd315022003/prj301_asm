/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

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
    private int sold;
    private LocalDate myObj = LocalDate.now();
    private int branding_id;
    private int filter_price_id;

    public Product() {
    }

    public Product(int product_id, String title, double price, double sale, String thumbnail, String description, int quantity, int sold, int branding_id, int filter_price_id) {
        this.product_id = product_id;
        this.title = title;
        this.price = price;
        this.sale = sale;
        this.thumbnail = thumbnail;
        this.description = description;
        this.quantity = quantity;
        this.sold = sold;
        this.branding_id = branding_id;
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

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public LocalDate getMyObj() {
        return myObj;
    }

    public void setMyObj(LocalDate myObj) {
        this.myObj = myObj;
    }

    public int getBranding_id() {
        return branding_id;
    }

    public void setBranding_id(int branding_id) {
        this.branding_id = branding_id;
    }

    public int getFilter_price_id() {
        return filter_price_id;
    }

    public void setFilter_price_id(int filter_price_id) {
        this.filter_price_id = filter_price_id;
    }

    @Override
    public String toString() {
        return "Product{" + "product_id=" + product_id + ", title=" + title + ", price=" + price + ", sale=" + sale + ", thumbnail=" + thumbnail + ", description=" + description + ", quantity=" + quantity + ", sold=" + sold + ", myObj=" + myObj + ", branding_id=" + branding_id + ", filter_price_id=" + filter_price_id + '}';
    }
    
    
    
}
