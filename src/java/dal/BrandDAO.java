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
                    + "  FROM [dbo].[Branding]";
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

}
