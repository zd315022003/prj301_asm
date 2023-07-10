/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Colors {
    private int colors_id;
    private String color_code;

    public Colors() {
    }

    public Colors(int colors_id, String color_code) {
        this.colors_id = colors_id;
        this.color_code = color_code;
    }

    public int getColors_id() {
        return colors_id;
    }

    public void setColors_id(int colors_id) {
        this.colors_id = colors_id;
    }

    public String getColor_code() {
        return color_code;
    }

    public void setColor_code(String color_code) {
        this.color_code = color_code;
    }

    
}
