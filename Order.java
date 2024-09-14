import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.sql.*;

public class Order {
    private int orderID;
    private Employee employee;
    private List<Product> products;
    private double totalAmount;
    protected String status;
    
    public Order() {
        createOrderTable();
    	
    }

    public Order(int orderID, Employee employee, List<Product> products, String status) {
        this.orderID = orderID;
        this.employee = employee;
        this.products = products;
        this.status = status;
        calculateTotalAmount();
        createOrderTable();
        insertOrder();
    }
    public void updateOrder(int orderID, Employee employee, List<Product> products, String status) {//new Update (Mustafa) not sure 
        this.orderID = orderID;
        this.employee = employee;
        this.products = products;
        this.status = status;
        calculateTotalAmount();
        createOrderTable();
        updateOrder(orderID,this);
    }
    public static void createOrderTable() {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement smt = connection.prepareStatement(
                         "CREATE TABLE IF NOT EXISTS orders (" +
                                 "orderID INT PRIMARY KEY," +
                                 "employeeID INT," +
                                 "status VARCHAR(255))")) {

                int i = smt.executeUpdate();
                if (i == 0) {
                    System.out.println("Order table already exists! Data saved !");
                } else {
                    System.out.println("Order table created successfully!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertOrder() {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO orders (orderID, employeeID, status) VALUES (?, ?, ?)")) {

                preparedStatement.setInt(1, this.orderID);
                preparedStatement.setInt(2, this.employee.getEmployeeID());
                preparedStatement.setString(3, this.status);

                preparedStatement.executeUpdate();
                System.out.println("Order created successfully!");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void updateOrder(int orderId, Order updatedOrder) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE orders SET employeeID=?, status=? WHERE orderID=?")) {

                preparedStatement.setInt(1, updatedOrder.getEmployee().getEmployeeID());
                preparedStatement.setString(2, updatedOrder.getStatus());
                preparedStatement.setInt(3, orderId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Order with ID " + orderId + " updated successfully!");
                } else {
                    System.out.println("Order with ID " + orderId + " not found.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void deleteOrder(int orderID) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "DELETE FROM orders WHERE orderID = ?")) {

                preparedStatement.setInt(1, orderID);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Order deleted successfully!");
                } else {
                    System.out.println("Order with ID " + orderID + " not found.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // public static Order getOrderById(int orderId)  {
    //     String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
    //     String username = "root";
    //     String password = "";
    //     Class.forName("com.mysql.cj.jdbc.Driver");

    //     try (Connection connection = DriverManager.getConnection(url, username, password);
    //          PreparedStatement preparedStatement = connection.prepareStatement(
    //                  "SELECT * FROM orders WHERE orderID = ?")) {
    //         preparedStatement.setInt(1, orderId);

    //         try (ResultSet resultSet = preparedStatement.executeQuery()) {
    //             if (resultSet.next()) {
    //                 System.out.println(resultSet.getInt("orderID"));
    //                 System.out.println(resultSet.getInt("employeeID")); // Assuming employeeID is an integer
    //                 System.out.println(resultSet.getString("status"));

    //                 // Retrieve employee details (assuming you have an Employee class)
                   

    //                 return new Order(
    //                         resultSet.getInt("orderID"),
    //                         employee.getEmployeeById(resultSet.getInt("employeeID")),
    //                         null,
    //                         resultSet.getString("status")
    //                 );
    //             }
    //         }
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
        calculateTotalAmount();
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    private void calculateTotalAmount() {
        totalAmount = 0.0;
        for (Product product : products) {
            totalAmount += product.getExportPrice();
        }
    }

    public void updateOrderStatus(String newStatus) {
        status = newStatus;
    }
    
    

    public void readFromJsonFile(String fileName) {
        StringBuilder jsonText = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                jsonText.append(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Manually extract values
        String jsonString = jsonText.toString();
        this.orderID = Integer.parseInt(extractValue(jsonString, "orderID"));
        this.totalAmount = Double.parseDouble(extractValue(jsonString, "totalAmount"));
        this.status = extractValue(jsonString, "status");
    }

    private String extractValue(String json, String key) {
        String keySearch = "\"" + key + "\":";
        int startIndex = json.indexOf(keySearch);
        if (startIndex != -1) {
            startIndex += keySearch.length();
            int endIndex = json.indexOf(",", startIndex);
            if (endIndex == -1) {
                endIndex = json.indexOf("}", startIndex);
            }
            return json.substring(startIndex, endIndex).replaceAll("\"", "").trim();
        }
        return null;
    }

    public static void writeToJsonFile(String fileName, List<Order> orders) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < orders.size(); i++) {
                writer.write("    {\n");
                writer.write("        \"orderID\": " + orders.get(i).orderID + ",\n");
                writer.write("        \"totalAmount\": " + orders.get(i).totalAmount + ",\n");
                writer.write("        \"status\": \"" + orders.get(i).status + "\"\n");
                writer.write("    }");
                if (i < orders.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Orders data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString(){
        return  orderID +"  "+employee+"  "+ products+"  "+totalAmount+"  "+status ;
    }

}
