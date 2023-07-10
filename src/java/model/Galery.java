/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Galery {
    private int galery_id;
    private int product_id;
    private String thumbnail;

    public Galery() {
    }

    public Galery(int galery_id, int product_id, String thumbnail) {
        this.galery_id = galery_id;
        this.product_id = product_id;
        this.thumbnail = thumbnail;
    }

    public int getGalery_id() {
        return galery_id;
    }

    public void setGalery_id(int galery_id) {
        this.galery_id = galery_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    
}
