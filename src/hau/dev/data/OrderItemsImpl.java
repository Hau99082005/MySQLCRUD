package hau.dev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class OrderItemsImpl {
	private Connection conn;
	public OrderItemsImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	public void findAll() {
        String sql = "SELECT * FROM `order_items`";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                System.out.printf("ID: %d, quantity: %s, price: %s%n", quantity, price, order_id, product_id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	public void find() {
        String sql = "SELECT * FROM `order_items` WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 1); // test với id = 1
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                int order_id = rs.getInt("order_id");
                int product_id = rs.getInt("product_id");
                System.out.printf("ID: %d, id: %s, quantity: %s%n", id, quantity, price, order_id, product_id);
            } else {
                System.out.println("Không tìm thấy orderitems với ID = 1");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	 public void select() {
	        String sql = "SELECT * FROM `order_items`";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                int quantity = rs.getInt("quantity");
	                double price = rs.getDouble("price");
	                int order_id = rs.getInt("order_id");
	                int product_id = rs.getInt("product_id");
	                System.out.printf("ID: %d, id: %s, code: %s%n", id, quantity, price, order_id, product_id);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void delete() {
	        String sql = "DELETE FROM `order_items` WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, 1); // xóa id = 1
	            int rows = stmt.executeUpdate();
	            System.out.println("Đã xóa " + rows + " dòng");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void update() {
        String sql = "UPDATE `order_items` SET QUANTITY = ?, PRICE = ?, ORDER_ID = ?, PRODUCT_ID = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, 10);
            stmt.setDouble(2, 500.000);
            stmt.setInt(3, 1);
            stmt.setInt(4, 1);
            stmt.setInt(5, 1); // ID để update
            int rows = stmt.executeUpdate();
	            System.out.println("Đã cập nhật " + rows + " dòng");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public int insert(int orderId) {
        try {
            // Tạm thời vô hiệu hóa foreign key constraint
            PreparedStatement disableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.execute();
            System.out.println("Đã vô hiệu hóa foreign key constraint cho order_items");
            
            String sql = "INSERT INTO `order_items`(QUANTITY, PRICE, ORDER_ID, PRODUCT_ID) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, 200);
            stmt.setDouble(2, 600.000);
            stmt.setInt(3, orderId); // Sử dụng orderId được truyền vào
            stmt.setInt(4, 1);
            int rows = stmt.executeUpdate();
            
            // Lấy ID được tạo tự động
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int orderitemsId = 0;
            if (generatedKeys.next()) {
                orderitemsId = generatedKeys.getInt(1);
                System.out.println("Đã chèn " + rows + " dòng với ID: " + orderitemsId);
            }
            
            // Bật lại foreign key constraint
            PreparedStatement enableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.execute();
            System.out.println("Đã bật lại foreign key constraint cho order_items");
            
            return orderitemsId;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }
}
