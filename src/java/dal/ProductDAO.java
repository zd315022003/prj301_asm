/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.ProductDTO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Admin
 */
public class ProductDAO extends DBContext {

    public List<ProductDTO> getProductByFilter(String search, List<Integer> categories, List<Integer> brands) {
        Map<Integer, ProductDTO> result = new HashMap<>();
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
                        result.put(productDTO.getId(), productDTO);
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<>(result.values());
    }

}
