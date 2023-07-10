/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Admin
 */
public class Branding {
    private int branding_id;
    private String name;

    public Branding() {
    }

    public Branding(int branding_id, String name) {
        this.branding_id = branding_id;
        this.name = name;
    }

    public int getBranding_id() {
        return branding_id;
    }

    public void setBranding_id(int branding_id) {
        this.branding_id = branding_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
