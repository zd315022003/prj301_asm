package dal;

import dto.OrderDetailDTO;

import java.sql.PreparedStatement;
import java.util.List;

public class OrderDetailDAO extends DBContext {
    public void createOrderDetails(List<OrderDetailDTO> list)  {
        String sql = "INSERT INTO [dbo].[Order_details]\n" +
                "           ([order_id]\n" +
                "           ,[product_id]\n" +
                "           ,[num]\n" +
                "           ,[price])\n" +
                "     VALUES\n" +
                "           (?\n" +
                "           ,?\n" +
                "           ,?\n" +
                "           ,?)";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            for (OrderDetailDTO orderDetailDTO : list) {
                statement.setInt(1, orderDetailDTO.getOrderId());
                statement.setInt(2, orderDetailDTO.getCartItem().getProductID());
                statement.setInt(3, orderDetailDTO.getCartItem().getQuantity());
                statement.setDouble(4, orderDetailDTO.getCartItem().getProductPrice());
                statement.executeUpdate();
                String sql2 = "UPDATE [dbo].[Product]\n" +
                        "   SET [quantity] = [quantity] - ?\n" +
                        " WHERE [product_id] = ?";
                PreparedStatement statement2 = connection.prepareStatement(sql2);
                statement2.setInt(1, orderDetailDTO.getCartItem().getQuantity());
                statement2.setInt(2, orderDetailDTO.getCartItem().getProductID());
                statement2.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
