/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dto;

/**
 *
 * @author Admin
 */
public class ProfileDTO {
    private String image_url;
    private String username;
    private String first_name;
    private String last_name;
    private String email;
    private String currentpass;
    private String newpass;
    private String phoneNumber;

    public ProfileDTO() {
    }

    public ProfileDTO(String image_url, String username, String first_name, String last_name, String email, String currentpass, String newpass, String phoneNumber) {
        this.image_url = image_url;
        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.currentpass = currentpass;
        this.newpass = newpass;
        this.phoneNumber = phoneNumber;
    }

    public ProfileDTO(String imgUrl, String username, String firstName, String lastName, String email, String hash, String hash1) {
        this.image_url = imgUrl;
        this.username = username;
        this.first_name = firstName;
        this.last_name = lastName;
        this.email = email;
        this.currentpass = hash;
        this.newpass = hash1;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCurrentpass() {
        return currentpass;
    }

    public void setCurrentpass(String currentpass) {
        this.currentpass = currentpass;
    }

    public String getNewpass() {
        return newpass;
    }

    public void setNewpass(String newpass) {
        this.newpass = newpass;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    
}
