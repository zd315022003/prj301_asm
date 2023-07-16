/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.ProfileDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ProfileDAO extends DBContext {

    public ProfileDTO getprofile(String username) {
        try {
            String query = "SELECT image_url, first_name, last_name, email, phone_number FROM Account\n"
                    + "WHERE username = ?\n" +
                    "AND [status] = 1;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            ProfileDTO profile = null;
            if (rs.next()) {
                profile = new ProfileDTO();
                String Pimage_url = rs.getString("image_url");
                String Pfirst_name = rs.getString("first_name");
                String Plast_name = rs.getString("last_name");
                String Pemail = rs.getString("email");
                String Pphone = rs.getString("phone_number");
                profile.setImage_url(Pimage_url);
                profile.setUsername(username);
                profile.setFirst_name(Pfirst_name);
                profile.setLast_name(Plast_name);
                profile.setEmail(Pemail);
                profile.setPhoneNumber(Pphone);
            }
            return profile;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void change(ProfileDTO acc) {
        String sql = "    update [dbo].[account] \n"
                + "  set [image_url] = ?,\n"
                + "  [username] = ?,\n"
                + "  [first_name] = ?,\n"
                + "  [last_name] = ?,\n"
                + "  [email] = ?,\n"
                + "  [password] = ?\n"
                + "  where [username] = ?\n" +
                "AND [status] = 1;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getImage_url());
            ps.setString(2, acc.getUsername());
            ps.setString(3, acc.getFirst_name());
            ps.setString(4, acc.getLast_name());
            ps.setString(5, acc.getEmail());
            ps.setString(6, acc.getNewpass());
            ps.setString(7, acc.getUsername());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkPassword(String username, String pass) {
        try {
            String query = " select * from Account\n"
                    + "  where username = ?\n"
                    + "  and password = ?\n" +
                    "AND [status] = 1;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getprofileId(String username) {
        String sql = "select account_id from Account where username = ? and [status] = 1;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("account_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
