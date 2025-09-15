package hau.dev;
import java.sql.DriverManager;
import java.sql.Connection;

import hau.dev.data.CategoryImpl;
import hau.dev.data.ProductImpl;
import hau.dev.data.UserImpl;

public class MainApp {
    public static void main(String[] args) {
    	
        Connection conn = getConnection();
        
        CategoryImpl categoryImpl = new CategoryImpl(conn);
        int categoryId = categoryImpl.insert(); // Thêm category mới trước và lấy ID
        categoryImpl.update();
        categoryImpl.select();
        categoryImpl.find();
        categoryImpl.findAll();
         
         UserImpl userImpl = new UserImpl(conn);
         userImpl.insert();
         userImpl.update();
         userImpl.delete();
         userImpl.select();
         userImpl.find();
         userImpl.findAll();
         
         ProductImpl productImpl = new ProductImpl(conn);
         productImpl.insert(categoryId); // Thêm sản phẩm với category_id đúng
         productImpl.update();
         productImpl.select();
         productImpl.find();
         productImpl.findAll();
//         productImpl.delete(); // Xóa sản phẩm cuối cùng
         
//         categoryImpl.delete(); // Xóa category cuối cùng
    }

		private static Connection getConnection() {
		// TODO Auto-generated method stub
		final String DB_URL = "jdbc:mysql://localhost:3306/shop";
		final String USER = "root";
		final String PASS = "";
        try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, USER, PASS);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
