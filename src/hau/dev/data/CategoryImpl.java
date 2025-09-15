package hau.dev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryImpl {
	private Connection conn;
	
	public CategoryImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	   public void findAll() {
	        String sql = "SELECT * FROM CATEGORIES";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String thumbnail = rs.getString("thumbnail");
	                System.out.printf("ID: %d, Name: %s, Thumbnail: %s%n", id, name, thumbnail);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	   public void find() {
	        String sql = "SELECT * FROM CATEGORIES WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, 19); // test với id = 19
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String thumbnail = rs.getString("thumbnail");
	                System.out.printf("ID: %d, Name: %s, Thumbnail: %s%n", id, name, thumbnail);
	            } else {
	                System.out.println("Không tìm thấy category với ID = 19");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void select() {
	        String sql = "SELECT * FROM CATEGORIES";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String thumbnail = rs.getString("thumbnail");
	                System.out.printf("ID: %d, Name: %s, Thumbnail: %s%n", id, name, thumbnail);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void delete() {
	        String sql = "DELETE FROM CATEGORIES WHERE ID = ?";
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
	        String sql = "UPDATE CATEGORIES SET NAME = ?, THUMBNAIL = ? WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setString(1, "Hau");
	            stmt.setString(2, "https://image.com/2.jpg");
	            stmt.setInt(3, 1);
	            int rows = stmt.executeUpdate();
	            System.out.println("Đã cập nhật " + rows + " dòng");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public int insert() {
	        String sql = "INSERT INTO CATEGORIES(NAME, THUMBNAIL) VALUES(?, ?)";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
	            stmt.setString(1, "Ao Polo");
	            stmt.setString(2, "https://image.com/1.jpg");
	            int rows = stmt.executeUpdate();
	            
	            // Lấy ID được tạo tự động
	            ResultSet generatedKeys = stmt.getGeneratedKeys();
	            int categoryId = 0;
	            if (generatedKeys.next()) {
	                categoryId = generatedKeys.getInt(1);
	                System.out.println("Đã chèn " + rows + " dòng với ID: " + categoryId);
	            }
	            return categoryId;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }


}
