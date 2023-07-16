package dal;

import dto.OrderDTO;
import model.Orders;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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
                + "           ,1\n"
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
        String sql = "SELECT TOP 1 order_id FROM Orders WHERE status = 1 ORDER BY order_id DESC";
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

    public List<Orders> getAll() {
        List<Orders> results = new ArrayList<>();
        String query = "SELECT * FROM Orders";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Orders orders = new Orders();
                orders.setOrder_id(rs.getInt("order_id"));
                orders.setFirst_name(rs.getString("first_name"));
                orders.setLast_name(rs.getString("last_name"));
                orders.setEmail(rs.getString("email"));
                orders.setPhone_number(rs.getString("phone_number"));
                orders.setNote(rs.getString("note"));
                orders.setOrder_date(rs.getDate("order_date"));
                orders.setStatus(rs.getInt("status"));
                orders.setTotal_money(rs.getInt("total_money"));
                orders.setAccount_id(rs.getInt("account_id"));
                orders.setAddress(rs.getString("address"));
                results.add(orders);
            }
        } catch (Exception e) {
            Logger.getLogger(e.getMessage());
        }
        return results;
    }
}
