import java.sql.*;
import java.util.*;


public class Manager extends User implements ManagerActions {
    private List<String> roles;
    private List<Product> products;
    private List<Employee> employees;
    private List<Order> orders;

    public Manager() {
    }

    public Manager(int userID, String email, String password, List<String> roles) {
        super(userID, email, password);
        this.roles = roles;
        this.products = new ArrayList<>();
        createManagerTable();
        insertManager(this);
    }

    public void managerUpdate(int userID, String email, String password, List<String> roles) {
        this.roles = roles;
        updateManager(userID, this);
    }

    public static void createManagerTable() {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement smt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS managers (" +
                         "userID INT PRIMARY KEY," +
                         "email VARCHAR(255)," +
                         "password VARCHAR(255)," +
                         "roles VARCHAR(255))")) {

                int i = smt.executeUpdate();
                if (i == 0) {
                    System.out.println("Manager table already exists! Data saved!");
                } else {
                    System.out.println("Manager table created successfully!");
                }
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
    }

    public static void insertManager(Manager manager) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO managers VALUES (?, ?, ?, ?)")) {

                preparedStatement.setInt(1, manager.getUserID());
                preparedStatement.setString(2, manager.getEmail());
                preparedStatement.setString(3, manager.getPassword());
                preparedStatement.setString(4, String.join(",", manager.getRoles()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void updateManager(int userID, Manager updatedManager) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE managers SET email=?, password=?, roles=? WHERE userID=?")) {

                preparedStatement.setString(1, updatedManager.getEmail());
                preparedStatement.setString(2, updatedManager.getPassword());
                preparedStatement.setString(3, String.join(",", updatedManager.getRoles()));
                preparedStatement.setInt(4, userID);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Manager with ID " + userID + " updated successfully!");
                } else {
                    System.out.println("Manager with ID " + userID + " not found.");
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void deleteManager(int userID) {
    String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
    String username = "root";
    String password = "";

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "DELETE FROM managers WHERE userID = ?")) {

            preparedStatement.setInt(1, userID);
            int rowsDeleted = preparedStatement.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println("Manager with ID " + userID + " deleted successfully!");
            } else {
                System.out.println("Manager with ID " + userID + " not found.");
            }
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public void addProduct(Product product) {
        if (product != null) {
            
            products.add(product); 
            System.out.println("Product added: " + product.getName());
        } else {
            System.out.println("Product addition failed. Invalid product data.");
        }
    }

    public void removeProduct(Product product) {
        if (product != null) {

            if (products.remove(product)) {
                System.out.println("Product removed: " + product.getName());
            } else {
                System.out.println("Product removal failed. Product not found.");
            }
        } else {
            System.out.println("Product removal failed. Invalid product data.");
        }
    }


    // public void editProductInfo(int productID, String newName, String newCategory, double newImportPrice, double newExportPrice, String date, String date2) throws ParseException {
    // 	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //     Date importDate = dateFormat.parse(date);
    //     Date expireDate = dateFormat.parse(date2);
    //     for (Product product : products) {
    //         if (product.getProductID() == productID) {
                
    //             if (newName != null) {
    //                 product.setName(newName);
    //             }
    //             if (newCategory != null) {
    //                 product.setCategory(newCategory);
    //             }
    //             if (newImportPrice >= 0) {
    //                 product.setImportPrice(newImportPrice);
    //             }
    //             if (newExportPrice >= 0) {
    //                 product.setExportPrice(newExportPrice);
    //             }
    //              if (date != null) {
    //                  product.setImportDate(importDate);
    //              }
    //              if (date2 != null) {
    //                  product.setExpireDate(expireDate);
    //              }
    
    //             System.out.println("Product information updated: " + product.getName());
    //             return;
    //         }
    //     }
    //     System.out.println("Product information update failed. Product not found.");
    // }

    public void addEmployee(Employee employee) {
        if (employee != null) {

            employees.add(employee); 
            System.out.println("Employee added: " + employee.getEmail());
        } else {
            System.out.println("Employee addition failed. Invalid employee data.");
        }
    }

    

    public void specifyRoles(Employee employee, List<String> roles) {
        if (employee != null && roles != null) {

            employee.setRoles(roles);
            System.out.println("Roles specified for the employee successfully.");
        } else {
            System.out.println("Invalid employee or roles. Roles not specified.");
        }
    }


    public void makeAnnualReport() {
        double totalSales = 0.0;
    for (Order order : orders) {
        if (order.getStatus().equals("Delivered")) {
            // You may need to modify this logic based on your order status and payment tracking
            totalSales += order.getTotalAmount();
        }
    }

    // You can generate a report with more details, such as sales by product, etc.
    System.out.println("Annual report for the year:");
    System.out.println("Total Sales: $" + totalSales);
    }

    public void configureSystem() {
        System.out.println("System configured successfully.");
    }

    @Override
    public void updateEmployeeInfo(Employee employee, double newSalary, String newAddress) {

        if (employee != null) {
            employee.setSalary(newSalary);
            employee.setAddress(newAddress);
            System.out.println("Employee information updated successfully.");
        } else {
            System.out.println("Employee not found. Information update failed.");
        }
    }
    public String toString() {
    	return roles+" "+products +" "+employees+" "+orders;
    }

    
}
