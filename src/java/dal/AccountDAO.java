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
import java.util.ArrayList;
import java.util.List;
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
                + "AND [password] = ? \n" +
                "AND [status] = 1;";
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
                    + "where [username] = ?" ;
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
                    + "           ,[role_id]\n"
                    + "           ,[status])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?,1);";
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

    public List<Account> getAll() {
        List<Account> result = new ArrayList<>();
        String query = "SELECT [account_id],[username]\n"
                + "      ,[password]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[email]\n"
                + "      ,[phone_number]\n"
                + "      ,[role_id], [image_url], [status]\n"
                + "  FROM [dbo].[Account]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFirst_name(rs.getString("first_name"));
                acc.setLast_name(rs.getString("last_name"));
                acc.setEmail(rs.getString("email"));
                acc.setPhone_number(rs.getString("phone_number"));
                acc.setRole_id(rs.getInt("role_id"));
                acc.setImage_url(rs.getString("image_url"));
                acc.setStatus(rs.getBoolean("status"));
                result.add(acc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void delete(int id, int status) {
        String query = "UPDATE [dbo].[Account]\n"
                + "   SET [status] = ?\n"
                + " WHERE [account_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, status);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void makeAdmin(int id, int role_id) {
        String query = "UPDATE [dbo].[Account]\n"
                + "   SET [role_id] = ?\n"
                + " WHERE [account_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, role_id);
            ps.setInt(2, id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Account getById(int id) {
        String query = "SELECT [account_id],[username]\n"
                + "      ,[password]\n"
                + "      ,[first_name]\n"
                + "      ,[last_name]\n"
                + "      ,[email]\n"
                + "      ,[phone_number]\n"
                + "      ,[role_id]\n"
                + "  FROM [dbo].[Account]\n"
                + "where [account_id] = ?\n" +
                "AND [status] = 1;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setAccount_id(rs.getInt("account_id"));
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setFirst_name(rs.getString("first_name"));
                acc.setLast_name(rs.getString("last_name"));
                acc.setEmail(rs.getString("email"));
                acc.setPhone_number(rs.getString("phone_number"));
                acc.setRole_id(rs.getInt("role_id"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void update(Account account) {
        String query = "UPDATE [dbo].[Account]\n"
                + "   SET [username] = ?,[first_name] = ?\n"
                + "      ,[last_name] = ?\n"
                + "      ,[email] = ?\n"
                + "      ,[phone_number] = ?\n"
                + "      ,[role_id] = ?\n"
                + " WHERE [account_id] = ?\n" +
                "AND [status] = 1;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getFirst_name());
            ps.setString(3, account.getLast_name());
            ps.setString(4, account.getEmail());
            ps.setString(5, account.getPhone_number());
            ps.setInt(6, account.getRole_id());
            ps.setInt(7, account.getAccount_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getCurrentId(String account) {
        String query = "SELECT [account_id]\n"
                + "  FROM [dbo].[Account]\n"
                + "where [username] = ?\n" +
                "AND [status] = 1;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, account);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("account_id");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
}
