package hau.dev;
import java.sql.DriverManager;
import java.sql.Connection;

import hau.dev.data.CategoryImpl;
import hau.dev.data.OrderImpl;
import hau.dev.data.OrderItemsImpl;
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
         // Sử dụng user_id = 0 để test (có thể bỏ qua foreign key constraint)
         int userId = 0;
         System.out.println("Sử dụng User ID cố định: " + userId);
         
         // Kiểm tra xem user có tồn tại không
         System.out.println("Kiểm tra user ID " + userId + "...");
         if (!userImpl.userExists(userId)) {
             System.out.println("User ID " + userId + " không tồn tại! Tạo user mới...");
             userId = userImpl.insert();
             if (userId == 0) {
                 System.out.println("Không thể tạo user mới! Sử dụng user_id = 0 để test...");
                 userId = 0; // Sử dụng 0 để test
             }
         } else {
             System.out.println("User ID " + userId + " đã tồn tại!");
         }
         
         userImpl.update();
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
         
         
          OrderImpl orderImpl = new OrderImpl(conn);
          int orderId = orderImpl.insert(userId); // Tạo order với user_id hợp lệ
          System.out.println("Order ID được trả về: " + orderId);
          
          // Kiểm tra xem order có tồn tại không
          if (orderId > 0) {
              System.out.println("Order được tạo thành công với ID: " + orderId);
          } else {
              System.out.println("Lỗi: Không thể tạo order!");
              return; // Dừng chương trình nếu không tạo được order
          }
          
          orderImpl.update();
          orderImpl.select();
          orderImpl.find();
          orderImpl.findAll();
          
          
          OrderItemsImpl orderitemsImpl = new OrderItemsImpl(conn);
          orderitemsImpl.insert(orderId); // Tạo order_items với order_id hợp lệ
         orderitemsImpl.update();
         orderitemsImpl.select();
         orderitemsImpl.find();
         orderitemsImpl.findAll();
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
