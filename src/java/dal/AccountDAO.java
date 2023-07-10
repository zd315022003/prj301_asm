/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.AccountDTO;
import dto.ProfileDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

/**
 *
 * @author Admin
 */
public class AccountDAO extends DBContext {

    public AccountDTO login(String username, String password) {
        String query = "SELECT [username]\n"
                + "      ,[role_id]\n"
                + "  FROM [dbo].[Account]\n"
                + "where [username] = ?\n"
                + "AND [password] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            AccountDTO account = null;
            if (rs.next()) {
                account = new AccountDTO();
                String rsUsername = rs.getString("username");
                int rsRoleId = rs.getInt("role_id");
                account.setRoleId(rsRoleId);
                account.setUsername(rsUsername);
            }

            if (rs.next()) {
                throw new Exception("Too many rows found!");
            }
            return account;
        } catch (Exception e) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public boolean checkExisted(String username) {
        try {
            String query = "SELECT *\n"
                    + "  FROM [dbo].[Account]\n"
                    + "where [username] = ?;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void register(Account acc) {
        PreparedStatement ps = null;
        try {
            String query = "INSERT INTO [dbo].[Account]\n"
                    + "           ([first_name]\n"
                    + "           ,[last_name]\n"
                    + "           ,[email]\n"
                    + "           ,[phone_number]\n"
                    + "           ,[username]\n"
                    + "           ,[password]\n"
                    + "           ,[role_id])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?);";
            ps = connection.prepareStatement(query);
            ps.setString(1, acc.getFirst_name());
            ps.setString(2, acc.getLast_name());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getPhone_number());
            ps.setString(5, acc.getUsername());
            ps.setString(6, acc.getPassword());
            ps.setInt(7, acc.getRole_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
