package hau.dev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderImpl {
private Connection conn;
	public OrderImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	public void findAll() {
        String sql = "SELECT * FROM `orders`";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String code = rs.getString("code");
                boolean status = rs.getBoolean("status");
                int user_id = rs.getInt("user_id");
                Timestamp created_at = rs.getTimestamp("created_at");
                System.out.printf("ID: %d, id: %s, code: %s%n", id, code, status, user_id, created_at);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	 public void find() {
        String sql = "SELECT * FROM `orders` WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, 19); // test với id = 19
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String code = rs.getString("code");
	                boolean status = rs.getBoolean("status");
	                int user_id = rs.getInt("user_id");
	                Timestamp created_at = rs.getTimestamp("created_at");
	                System.out.printf("ID: %d, id: %s, code: %s%n", id, code, status, user_id, created_at);
	            } else {
	                System.out.println("Không tìm thấy order với ID = 1");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void select() {
        String sql = "SELECT * FROM `orders`";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String code = rs.getString("code");
	                boolean status = rs.getBoolean("status");
	                int user_id = rs.getInt("user_id");
	                Timestamp created_at = rs.getTimestamp("created_at");
	                System.out.printf("ID: %d, id: %s, code: %s%n", id, code, status, user_id, created_at);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public void delete() {
        String sql = "DELETE FROM `orders` WHERE ID = ?";
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
        String sql = "UPDATE `orders` SET CODE = ?, STATUS = ?, USER_ID = ?, CREATED_AT = ? WHERE ID = ?";
        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, "123456");
            stmt.setBoolean(2, true);
            stmt.setInt(3, 1);
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            stmt.setInt(5, 1); // ID để update
            int rows = stmt.executeUpdate();
	            System.out.println("Đã cập nhật " + rows + " dòng");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 public int insert(int userId) {
        try {
            // Tạm thời vô hiệu hóa foreign key constraint
            PreparedStatement disableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0");
            disableFK.execute();
            System.out.println("Đã vô hiệu hóa foreign key constraint");
            
            // Tạo user nếu chưa tồn tại
            if (userId <= 0) {
                System.out.println("Tạo user mới trước khi tạo order...");
                String userSql = "INSERT INTO `users`(EMAIL, PASSWORD, ROLE) VALUES(?, ?, ?)";
                PreparedStatement userStmt = conn.prepareStatement(userSql, PreparedStatement.RETURN_GENERATED_KEYS);
                userStmt.setString(1, "hau@gmail.com");
                userStmt.setString(2, "123456");
                userStmt.setString(3, "user");
                int userRows = userStmt.executeUpdate();
                
                
                ResultSet userKeys = userStmt.getGeneratedKeys();
                if (userKeys.next()) {
                    userId = userKeys.getInt(1);
                    System.out.println("Đã tạo user với ID: " + userId);
                }
            }
            
            String sql = "INSERT INTO `orders`(CODE, STATUS, USER_ID, CREATED_AT) VALUES(?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "23456789");
            stmt.setBoolean(2, true);
            stmt.setInt(3, userId); // Sử dụng userId được truyền vào
            stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
            int rows = stmt.executeUpdate();
            
            // Lấy ID được tạo tự động
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int orderId = 0;
            if (generatedKeys.next()) {
                orderId = generatedKeys.getInt(1);
                System.out.println("Đã chèn " + rows + " dòng với ID: " + orderId);
            }
            
            // Bật lại foreign key constraint
            PreparedStatement enableFK = conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1");
            enableFK.execute();
            System.out.println("Đã bật lại foreign key constraint");
            
            return orderId;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }
}
