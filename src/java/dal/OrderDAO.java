package dal;

import dto.OrderDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OrderDAO extends DBContext {

    public int createOrder(OrderDTO orderDTO) {
        String sql = "INSERT INTO [dbo].[Orders]\n"
                + "           ([first_name]\n"
                + "           ,[last_name]\n"
                + "           ,[email]\n"
                + "           ,[phone_number]\n"
                + "           ,[note]\n"
                + "           ,[order_date]\n"
                + "           ,[status]\n"
                + "           ,[total_money]\n"
                + "           ,[account_id]\n"
                + "           ,[address])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,GETDATE()\n"
                + "           ,0\n"
                + "           ,0\n"
                + "           ,?\n"
                + "           ,?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, orderDTO.getFirst_name());
            preparedStatement.setString(2, orderDTO.getLast_name());
            preparedStatement.setString(3, orderDTO.getEmail());
            preparedStatement.setString(4, orderDTO.getPhone_number());
            preparedStatement.setString(5, orderDTO.getNote());
            preparedStatement.setInt(6, orderDTO.getAccount_id());
            preparedStatement.setString(7, orderDTO.getAddress());
            preparedStatement.executeUpdate();
            connection.commit();
            return getLastestOrderId();
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }

    private int getLastestOrderId() {
        String sql = "SELECT TOP 1 order_id FROM Orders ORDER BY order_id DESC";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("order_id");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return -1;
    }
}
