/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.CategoryDTO;
import model.Category;
import model.Product;

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
                    + "  FROM [dbo].[Category]\n" +
                    "  WHERE [status] = 1";
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
                if (categoryDTO != null) {
                    categoryDTO.setCount(rs.getInt("count"));
                }
            }
            rs.close();
            ps.close();

        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(results.values());
    }

    public void insert(String categoryName) {
        String query = "INSERT INTO [dbo].[Category]\n"
                + "           ([name], [status])\n"
                + "     VALUES\n"
                + "           (?, 1)";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, categoryName);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Category> getCategoriesByProduct(Product product) {
        String query = "SELECT [C].[category_id]\n" +
                "     , [C].[name]\n" +
                "FROM [dbo].[Product] P\n" +
                "JOIN ProductCategory PC on P.product_id = PC.ProductID\n" +
                "JOIN Category C on PC.CategoryID = C.category_id\n" +
                "WHERE [product_id] = ?\n" +
                "AND [C].[status] = 1";
        List<Category> categories = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product.getProduct_id());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category();
                category.setCategory_id(rs.getInt("category_id"));
                category.setName(rs.getString("name"));
                categories.add(category);
            }
            rs.close();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }

    public void delete(int id) {
        String query = "UPDATE [dbo].[category]\n"
                + "   SET [status] = 0\n"
                + " WHERE [category_id] = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
