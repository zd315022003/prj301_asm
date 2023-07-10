/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

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
public class CategoryDAO extends DBContext {

    public List<CategoryDTO> getAll() {
        Map<Integer, CategoryDTO> results = new HashMap<>();
        try {
            String query = "SELECT [category_id]\n"
                    + "      ,[name]\n"
                    + "  FROM [dbo].[Category]";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoryDTO categoryDTO = new CategoryDTO();
                categoryDTO.setId(rs.getInt("category_id"));
                categoryDTO.setName(rs.getString("name"));
                categoryDTO.setCount(0);
                results.put(categoryDTO.getId(), categoryDTO);
            }
            rs.close();
            ps.close();
            query = "SELECT COUNT([ProductID]) AS [count]\n"
                    + "      ,[CategoryID]\n"
                    + "  FROM [dbo].[ProductCategory]\n"
                    + "  GROUP BY [CategoryID]";
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                CategoryDTO categoryDTO = results.get(rs.getInt("CategoryID"));
                categoryDTO.setCount(rs.getInt("count"));
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(results.values());
    }

}
