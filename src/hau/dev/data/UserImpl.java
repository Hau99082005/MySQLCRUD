package hau.dev.data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImpl {
    private Connection conn;

	public UserImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	 public void findAll() {
	        String sql = "SELECT * FROM USERS";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String email = rs.getString("email");
	                String password = rs.getString("password");
	                String role = rs.getString("role");
	                System.out.printf("ID: %d, email: %s, password: %s%n", id, email, password, role);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   public void find() {
	        String sql = "SELECT * FROM USERS WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1,1); // test với id = 19
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String email = rs.getString("email");
	                String password = rs.getString("password");
                    String role = rs.getString("role");
	                System.out.printf("ID: %d, Name: %s, Thumbnail: %s%n", id, email, password, role);
	            } else {
	                System.out.println("Không tìm thấy category với ID = 19");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	   
	
	public void insert() {
		String sql = "INSERT INTO USERS(ID, EMAIL, PASSWORD, ROLE) VALUES (null, ?, ?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "hau@gmail.com");
			stmt.setString(2, "123456");
			stmt.setString(3, "user");
			stmt.execute();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	public void update() {
		String sql = "UPDATE USERS SET email = ?, password = ?, role = ? WHERE id = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, "hau123@gmail.com");
			stmt.setString(2, "22082005");
			stmt.setString(3, "user");
			
			stmt.setInt(4, 1);
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	 public void select() {
	        String sql = "SELECT * FROM USERS";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String email = rs.getString("email");
	                String password = rs.getString("password");
	                String role = rs.getString("role");
	                System.out.printf("ID: %d, Name: %s, Thumbnail: %s%n", id, email, password, role);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    public void delete() {
	        String sql = "DELETE FROM USERS WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1, 1); // xóa id = 1
	            int rows = stmt.executeUpdate();
	            System.out.println("Đã xóa " + rows + " dòng");
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

}
