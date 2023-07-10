/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.CartItemDTO;
import dto.ProductDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<ProductDTO> getProductByFilter(String search, List<Integer> categories, List<Integer> brands, String sortby) {
        List<ProductDTO> result = new ArrayList<>();
        try {
            StringBuilder query = new StringBuilder("SELECT [product_id]\n"
                    + "      ,[title]\n"
                    + "      ,[price]\n"
                    + "      ,[sale]\n"
                    + "      ,[thumbnail]\n"
                    + "  FROM [dbo].[Product] p\n");
            if (Optional.ofNullable(categories).map(List::isEmpty).orElse(Boolean.FALSE)) {
            } else {
                query.append("JOIN [dbo].[ProductCategory] pc\n"
                        + "  ON [p].[product_id] = [pc].[ProductID]\n");
            }
            query.append("WHERE [title] like ?\n");
            if (Optional.ofNullable(brands).map(List::isEmpty).orElse(Boolean.FALSE)) {
            } else {
                query.append("AND [branding_id] in (\n");
                query.append(brands.stream().map(b -> "?")
                        .collect(Collectors.joining(",\n")));
                query.append(")");
            }
            if (Optional.ofNullable(categories).map(List::isEmpty).orElse(Boolean.FALSE)) {
            } else {
                query.append("AND [CategoryID] in (\n");
                query.append(categories.stream()
                        .map(b -> "?").collect(Collectors.joining(",\n")));
                query.append(")");
            }
            query.append(sortby);
            query.append(";");
            System.out.println(query.toString());
            try (PreparedStatement ps = connection.prepareStatement(query.toString())) {
                ps.setString(1, "%" + search + "%");
                for (int i = 0; i < Optional.ofNullable(brands).map(List::size).orElse(0); i++) {
                    ps.setInt(i + 2, brands.get(i));
                }
                for (int i = 0; i < Optional.ofNullable(categories).map(List::size).orElse(0); i++) {
                    ps.setInt(i + Optional.ofNullable(brands)
                            .map(List::size).orElse(0) + 2, categories.get(i));
                }
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        ProductDTO productDTO = new ProductDTO();
                        productDTO.setId(rs.getInt("product_id"));
                        productDTO.setPrice(rs.getDouble("price"));
                        productDTO.setSale(rs.getDouble("sale"));
                        productDTO.setThumbnail(rs.getString("thumbnail"));
                        productDTO.setTitle(rs.getString("title"));
                        result.add(productDTO);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public List<CartItemDTO> getCartItems(Set<Integer> listIds) {
        List<CartItemDTO> result = new ArrayList<>();
        try {
            StringBuilder script = new StringBuilder("SELECT [product_id],\n" +
                    "       [title],\n" +
                    "       [sale],\n" +
                    "       [thumbnail]\n" +
                    "FROM [dbo].[Product]\n" +
                    "WHERE [product_id] in (");
            if (listIds == null || listIds.isEmpty()) {
                return result;
            }
            script.append(listIds.stream().map(id -> "?")
                    .collect(Collectors.joining(",\n")));
            script.append(");");
            PreparedStatement ps = connection.prepareStatement(script.toString());
            for (int i = 0; i < listIds.size(); i++) {
                ps.setInt(i + 1, (int) listIds.toArray()[i]);
            }
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CartItemDTO cartItemDTO = new CartItemDTO();
                cartItemDTO.setProductID(rs.getInt("product_id"));
                cartItemDTO.setProductName(rs.getString("title"));
                cartItemDTO.setProductPrice(rs.getDouble("sale"));
                cartItemDTO.setProductImage(rs.getString("thumbnail"));
                cartItemDTO.setQuantity(0);
                result.add(cartItemDTO);
            }
            rs.close();
            ps.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}
