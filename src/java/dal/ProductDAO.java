/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dto.CartItemDTO;
import dto.ProductDTO;
import dto.ProductInfoDTO;
import model.Product;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
            query.append(" AND [status] = 1;");
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

    public List<ProductDTO> getProductByBrandingId(int product_id, int branding_id) {
        try {
            List<ProductDTO> product = new ArrayList<>();
            String query = "SELECT TOP 4 *\n"
                    + "FROM Product\n"
                    + "WHERE branding_id = ?\n"
                    + "AND product_id != ?\n"
                    + "AND [status] = 1;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, branding_id);
            ps.setInt(2, product_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("product_id"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setSale(rs.getDouble("sale"));
                productDTO.setThumbnail(rs.getString("thumbnail"));
                productDTO.setTitle(rs.getString("title"));
                product.add(productDTO);
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Product getProductById(int product_id) {
        try {
            String query = "SELECT * FROM Product\n"
                    + "WHERE product_id = ?\n"
                    + "AND [status] = 1;";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product();
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                double sale = rs.getDouble("sale");
                int branding_id = rs.getInt("branding_id");
                String thumbnail = rs.getString("thumbnail");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int sold = rs.getInt("sold");
                product.setProduct_id(product_id);
                product.setTitle(title);
                product.setPrice(price);
                product.setSale(sale);
                product.setBranding_id(branding_id);
                product.setThumbnail(thumbnail);
                product.setDescription(description);
                product.setQuantity(quantity);
                product.setSold(sold);
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> getProductBestSellers() {
        try {
            List<ProductDTO> product = new ArrayList<>();
            String query = "SELECT TOP 8 *\n"
                    + "FROM [dbo].[Product]\n"
                    + "WHERE [status] = 1\n"
                    + "ORDER BY sold DESC;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("product_id"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setSale(rs.getDouble("sale"));
                productDTO.setThumbnail(rs.getString("thumbnail"));
                productDTO.setTitle(rs.getString("title"));
                product.add(productDTO);
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> getProductSale() {
        try {
            List<ProductDTO> product = new ArrayList<>();
            String query = "SELECT TOP 4 *\n"
                    + "FROM [dbo].[Product]\n"
                    + "WHERE [status] = 1\n"
                    + "ORDER BY (price - sale) DESC;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("product_id"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setSale(rs.getDouble("sale"));
                productDTO.setThumbnail(rs.getString("thumbnail"));
                productDTO.setTitle(rs.getString("title"));
                product.add(productDTO);
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<ProductDTO> getProductNew() {
        try {
            List<ProductDTO> product = new ArrayList<>();
            String query = "SELECT TOP 4 *\n"
                    + "FROM Product\n"
                    + "WHERE [status] = 1\n"
                    + "ORDER BY create_at DESC;";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setId(rs.getInt("product_id"));
                productDTO.setPrice(rs.getDouble("price"));
                productDTO.setSale(rs.getDouble("sale"));
                productDTO.setThumbnail(rs.getString("thumbnail"));
                productDTO.setTitle(rs.getString("title"));
                product.add(productDTO);
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CartItemDTO> getCartItems(Set<Integer> listIds) {
        List<CartItemDTO> result = new ArrayList<>();
        try {
            StringBuilder script = new StringBuilder("SELECT [product_id],\n"
                    + "       [title],\n"
                    + "       [sale],\n"
                    + "       [thumbnail]\n"
                    + "FROM [dbo].[Product]\n"
                    + "WHERE [product_id] in (");
            if (listIds == null || listIds.isEmpty()) {
                return result;
            }
            script.append(listIds.stream().map(id -> "?")
                    .collect(Collectors.joining(",\n")));
            script.append(") AND [status] = 1;");
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

    public List<Product> getAll() {
        List<Product> result = new ArrayList<>();
        String query = "SELECT [product_id]\n"
                + "      ,[title]\n"
                + "      ,[price]\n"
                + "      ,[sale]\n"
                + "      ,[branding_id]\n"
                + "      ,[thumbnail]\n"
                + "      ,[description]\n"
                + "      ,[quantity]\n"
                + "      ,[sold]\n"
                + "      ,[create_at]\n"
                + "  FROM [dbo].[Product]\n"
                + "  WHERE [status] = 1";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product product = new Product();
                int product_id = rs.getInt("product_id");
                String title = rs.getString("title");
                double price = rs.getDouble("price");
                double sale = rs.getDouble("sale");
                int branding_id = rs.getInt("branding_id");
                String thumbnail = rs.getString("thumbnail");
                String description = rs.getString("description");
                int quantity = rs.getInt("quantity");
                int sold = rs.getInt("sold");
                Date create_at = rs.getDate("create_at");
                product.setProduct_id(product_id);
                product.setTitle(title);
                product.setPrice(price);
                product.setSale(sale);
                product.setBranding_id(branding_id);
                product.setThumbnail(thumbnail);
                product.setDescription(description);
                product.setQuantity(quantity);
                product.setSold(sold);
                product.setCreated_at(create_at);
                result.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public void delete(int deleteId) {
        String query = "UPDATE [dbo].[Product]\n"
                + "   SET [status] = 0\n"
                + "      WHERE product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, deleteId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteAll(List<Integer> idList) {
        StringBuilder script = new StringBuilder("UPDATE [dbo].[Product]\n"
                + "   SET [status] = 0\n"
                + "      WHERE product_id in (");
        if (idList == null || idList.isEmpty()) {
            return;
        }
        script.append(idList.stream().map(id -> "?")
                .collect(Collectors.joining(",\n")));
        script.append(");");
        try {
            PreparedStatement ps = connection.prepareStatement(script.toString());
            for (int i = 0; i < idList.size(); i++) {
                ps.setInt(i + 1, idList.get(i));
            }
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void updateProduct(Product product) {
        String query = "UPDATE [dbo].[Product]\n"
                + "   SET [title] = ?\n"
                + "      ,[price] = ?\n"
                + "      ,[sale] = ?\n"
                + "      ,[branding_id] = ?\n"
                + "      ,[thumbnail] = ?\n"
                + "      ,[description] = ?\n"
                + "      ,[quantity] = ?\n"
                + " WHERE product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, product.getTitle());
            ps.setDouble(2, product.getPrice());
            ps.setDouble(3, product.getSale());
            ps.setInt(4, product.getBranding_id());
            ps.setString(5, product.getThumbnail());
            ps.setString(6, product.getDescription());
            ps.setInt(7, product.getQuantity());
            ps.setInt(8, product.getProduct_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateProductCategories(Product product, List<Integer> categories) {
        if (product.getProduct_id() != -1) {

            String query = "DELETE FROM [dbo].[ProductCategory]\n"
                    + "      WHERE [ProductID] = ?";
            try {
                PreparedStatement ps = connection.prepareStatement(query);
                ps.setInt(1, product.getProduct_id());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query2 = "INSERT INTO [dbo].[ProductCategory]\n"
                    + "           ([ProductID]\n"
                    + "           ,[CategoryID])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query2);
                for (int i = 0; i < categories.size(); i++) {
                    ps.setInt(1, product.getProduct_id());
                    ps.setInt(2, categories.get(i));
                    ps.executeUpdate();
                }
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String script = "INSERT INTO [dbo].[Product]" +
                    "           ([title]" +
                    "           ,[price]" +
                    "           ,[sale]" +
                    "           ,[branding_id]" +
                    "           ,[thumbnail]" +
                    "           ,[description]" +
                    "           ,[quantity]" +
                    "           ,[sold]" +
                    "           ,[create_at]" +
                    "           ,[status])" +
                    "     VALUES" +
                    "           (?,?,?,?,?,?,?,0,GETDATE(),1)";
            try {
                PreparedStatement ps = connection.prepareStatement(script);
                ps.setString(1, product.getTitle());
                ps.setDouble(2, product.getPrice());
                ps.setDouble(3, product.getSale());
                ps.setInt(4, product.getBranding_id());
                ps.setString(5, product.getThumbnail());
                ps.setString(6, product.getDescription());
                ps.setInt(7, product.getQuantity());
                ps.executeUpdate();
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query2 = "SELECT TOP 1 product_id FROM [dbo].[Product] ORDER BY product_id DESC";
            int pid = -1;
            try {
                PreparedStatement ps = connection.prepareStatement(query2);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    product.setProduct_id(rs.getInt("product_id"));
                }
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
            String query3 = "INSERT INTO [dbo].[ProductCategory]\n"
                    + "           ([ProductID]\n"
                    + "           ,[CategoryID])\n"
                    + "     VALUES\n"
                    + "           (?,?)";
            try {
                PreparedStatement ps = connection.prepareStatement(query3);
                for (int i = 0; i < categories.size(); i++) {
                    ps.setInt(1, product.getProduct_id());
                    ps.setInt(2, categories.get(i));
                    ps.executeUpdate();
                }
                ps.close();
            } catch (SQLException ex) {
                Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public ProductInfoDTO getProductInfo() {
        String query = "SELECT SUM(quantity) as [remainingCount], SUM(sold) as [soldCount] FROM [dbo].[Product]";
        ProductInfoDTO productInfoDTO = new ProductInfoDTO();
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                productInfoDTO.setRemainingCount(rs.getInt("remainingCount"));
                productInfoDTO.setSoldCount(rs.getInt("soldCount"));
            }
            ps.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productInfoDTO;
    }
}
