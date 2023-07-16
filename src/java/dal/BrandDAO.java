/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.BrandDTO;
import dto.CategoryDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class BrandDAO extends DBContext {

    public List<BrandDTO> getAll() {
        Map<Integer, BrandDTO> results = new HashMap<>();
        try {
            String query = "SELECT [branding_id]\n"
                    + "      ,[name]\n"
                    + "  FROM [dbo].[Branding]\n" +
                    "  WHERE [status] = 1";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BrandDTO brandDTO = new BrandDTO();
                brandDTO.setId(rs.getInt("branding_id"));
                brandDTO.setName(rs.getString("name"));
                brandDTO.setCount(0);
                results.put(brandDTO.getId(), brandDTO);
            }
            rs.close();
            ps.close();
            query = "SELECT COUNT([product_id]) AS [count]\n"
                    + "      ,[branding_id]\n"
                    + "  FROM [dbo].[Product]\n"
                    + "  GROUP BY [branding_id]";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                BrandDTO brandDTO = results.get(rs.getInt("branding_id"));
                brandDTO.setCount(rs.getInt("count"));
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(results.values());
    }

    public void add(String brandName) {
        String query = "INSERT INTO [dbo].[Branding]\n"
                + "           ([name], [status])\n"
                + "     VALUES\n"
                + "           (?, 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, brandName);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int id) {
        String query = "UPDATE [dbo].[Branding]\n"
                + "   SET [status] = 0\n"
                + " WHERE [branding_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<BrandDTO> getAllBySale() {
        String query = "SELECT [B].[branding_id], [B].[name], SUM([sold]) AS [count]\n" +
                "FROM [dbo].[Product] P\n" +
                "RIGHT JOIN [dbo].[Branding] B on [P].[branding_id] = [b].[branding_id]\n" +
                "WHERE [B].[status] = 1\n" +
                "GROUP BY [B].[branding_id], [B].[name]";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            List<BrandDTO> results = new ArrayList<>();
            while (rs.next()) {
                BrandDTO brandDTO = new BrandDTO();
                brandDTO.setId(rs.getInt("branding_id"));
                brandDTO.setName(rs.getString("name"));
                brandDTO.setCount(rs.getInt("count"));
                results.add(brandDTO);
            }
            rs.close();
            ps.close();
            return results;
        } catch (SQLException ex) {
            Logger.getLogger(BrandDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
