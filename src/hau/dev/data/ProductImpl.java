package hau.dev.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ProductImpl {
	private Connection conn;

	public ProductImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	
	 public void findAll() {
	        String sql = "SELECT * FROM PRODUCTS";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String image = rs.getString("image");
	                String description = rs.getString("description");
	                double price = rs.getDouble("price");
	                double price_old = rs.getDouble("price_old");
	                int quantity = rs.getInt("quantity");
	                int view = rs.getInt("view");
	                int category_id  = rs.getInt("category_id");
	                Timestamp created_at = rs.getTimestamp("created_at");
	                System.out.printf("ID: %d, name: %s, image: %s%n", id, name, image, description, price, price_old, quantity, view, category_id, created_at);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
    
	 
	 public void find() {
	        String sql = "SELECT * FROM PRODUCTS WHERE ID = ?";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            stmt.setInt(1,1); // test với id = 19
	            ResultSet rs = stmt.executeQuery();
	            if (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String image = rs.getString("image");
                    String description = rs.getString("description");
                    double price = rs.getDouble("price");
                    double price_old = rs.getDouble("price_old");
                    int quantity = rs.getInt("quantity");
                    int view = rs.getInt("view");
                    int category_id  = rs.getInt("category_id");
                    Timestamp created_at = rs.getTimestamp("created_at");
	                System.out.printf("ID: %d, name: %s, image: %s%n", id, name, image, description, price, price_old, quantity, 
	                view, category_id, created_at);
	            } else {
	                System.out.println("Không tìm thấy products với ID = 1");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 public void insert(int categoryId) {    
			String sql = "INSERT INTO PRODUCTS(ID, NAME, IMAGE, DESCRIPTION, PRICE, PRICE_OLD, QUANTITY, VIEW, CATEGORY_ID, CREATED_AT) VALUES (null, ?, ?,?,?,?,?,?,?,?)";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "Áo thun oversize ngắn tay ,áo phông in hoạt hình hello kitty 15 chất cotton thoáng mát dễ mặc hotrend Hàn");
				stmt.setString(2, "assets/image/vn-11134207-7ra0g-m7ubly5y74hvaf@resize_w900_nl.webp");
				stmt.setString(3, "Áo thun oversize ngắn tay ,áo phông in hoạt hình hello kitty 15 chất cotton thoáng mát dễ mặc hotrend Hàn");
				stmt.setDouble(4, 300.000);
				stmt.setDouble(5, 700.000);
				stmt.setInt(6, 100);
				stmt.setInt(7, 10);
				stmt.setInt(8, categoryId); // Sử dụng category_id được truyền vào
				stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
				stmt.execute();
				System.out.println("Đã thêm sản phẩm với category_id: " + categoryId);
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	 
	 public void update() {
			String sql = "UPDATE PRODUCTS SET name = ?, image = ?, description = ?, price = ?, price_old = ?, quantity = ?, view = ?, category_id = ?, created_at = ? WHERE id = ?";
			try {
				PreparedStatement stmt = conn.prepareStatement(sql);
				stmt.setString(1, "[BST2 BỘ QUẦN ÁO NAM] Đồ bộ thể thao nam mặc hè, Chất thun cotton Họa Tiết Hottrend");
				stmt.setString(2, "assets/image/vn-11134258-820l4-mefk7cfgkttw53.png");
				stmt.setString(3, "[BST2 BỘ QUẦN ÁO NAM] Đồ bộ thể thao nam mặc hè, Chất thun cotton Họa Tiết Hottrend");
				stmt.setDouble(4, 500.000);
				stmt.setDouble(5, 900.000);
				stmt.setInt(6, 90);
				stmt.setInt(7, 20);
				stmt.setInt(8, 1); // Sử dụng category_id = 1 (category vừa được thêm mới)
				stmt.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
				stmt.setInt(10, 1);
				stmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	 public void select() {
	        String sql = "SELECT * FROM PRODUCTS";
	        try {
	            PreparedStatement stmt = conn.prepareStatement(sql);
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                int id = rs.getInt("id");
	                String name = rs.getString("name");
	                String image = rs.getString("image");
	                String description = rs.getString("description");
	                double price = rs.getDouble("price");
	                double price_old = rs.getDouble("price_old");
	                int quantity = rs.getInt("quantity");
	                int view = rs.getInt("view");
	                int category_id = rs.getInt("category_id");
	                Timestamp created_at = rs.getTimestamp("created_at");
	                System.out.printf("ID: %d, name: %s, image: %s%n", id, name, image, description, price, price_old, quantity, view, category_id, created_at);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    public void delete() {
	        String sql = "DELETE FROM PRODUCTS WHERE ID = ?";
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
