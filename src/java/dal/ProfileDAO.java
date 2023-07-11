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
            String query = "SELECT image_url, first_name, last_name, email FROM Account\n"
                    + "WHERE username = ?\n";
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
                profile.setImage_url(Pimage_url);
                profile.setUsername(username);
                profile.setFirst_name(Pfirst_name);
                profile.setLast_name(Plast_name);
                profile.setEmail(Pemail);
            }
            return profile;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void change(ProfileDTO acc) {
        String sql = "  update [account] \n"
                + "  set [password] = ?\n"
                + "  where [username] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getNewpass());
            ps.setString(2, acc.getUsername());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean checkPassword(String username, String pass) {
        try {
            String query = " select * from Account\n"
                    + "  where username = ?\n"
                    + "  and password = ?;";
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
}
