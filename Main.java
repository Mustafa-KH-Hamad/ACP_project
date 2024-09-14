import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
	        String username = "root";
	        String password = ""; 
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        
	        try (Connection connection = DriverManager.getConnection(url, username, password);
	             PreparedStatement preparedStatement = connection.prepareStatement(
	                     "SELECT * FROM products WHERE productID = ?")) {
	            preparedStatement.setInt(1, 1);

	            ResultSet resultSet = preparedStatement.executeQuery(); 
	                while (resultSet.next()) {
                                System.out.println( resultSet.getInt("productID"));
	                            System.out.println( resultSet.getString("name"));
	                            System.out.println( resultSet.getString("category"));
	                            System.out.println( resultSet.getDouble("importPrice"));
	                            System.out.println( resultSet.getDouble("exportPrice"));
	                            System.out.println( resultSet.getDate("importDate"));
	                            System.out.println( resultSet.getDate("expireDate"));        
	                }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

    }
}
