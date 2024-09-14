import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.*;

public class Employee extends User  implements EmployeeActions , Serializable{
    private int employeeID;
    private double salary;
    private String address;
    public List<Order> previousSales;//	its not completed .(Mustafa) 
    private List<String> roles;
    
    public Employee() {
    	super();
        createEmployeeTable();
    }
    
    public Employee(int userID, String email, String password, int employeeID, double salary, String address) {
        super(userID, email, password);
        this.employeeID = employeeID;
        this.salary = salary;
        this.address = address;
        this.roles = new ArrayList<>();
        this.previousSales = new ArrayList<>();
        createEmployeeTable();
        insertEmployee(this);
    }
     public void  EmployeeUpdate(int userID, String email, String password, int employeeID, double salary, String address) {
        this.employeeID = employeeID;
        this.salary = salary;
        this.address = address;
        this.roles = new ArrayList<>();
        this.previousSales = new ArrayList<>();
        updateEmployee(employeeID, this);
        
    }
    
    public void productUpdate(Product product , int productID,String name, String category, double importPrice, double exportPrice, Date importDate, Date expireDate) {
    	product.productUpdate(productID ,name , category , importPrice  , exportPrice , importDate , expireDate );
    	System.out.println("update was Successful ");
    	//Mustafa not sure
    }
    public static void createEmployeeTable() {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement smt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS employees (" +
                         "employeeID INT PRIMARY KEY," +
                         "userID INT," +
                         "email VARCHAR(255)," +
                         "password VARCHAR(255)," +
                         "salary DOUBLE," +
                         "address VARCHAR(255))")) {
    
                int i = smt.executeUpdate();
                if (i == 0) {
                    System.out.println("Employee table already exists! Data saved!");
                } else {
                    System.out.println("Employee table created successfully!");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the ClassNotFoundException appropriately
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the SQLException appropriately
        }
    }
    public static void insertEmployee(Employee employee) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO employees VALUES (?, ?, ?, ?, ?, ?)")) {
    
                preparedStatement.setInt(1, employee.getEmployeeID());
                preparedStatement.setInt(2, employee.getUserID());
                preparedStatement.setString(3, employee.getEmail());
                preparedStatement.setString(4, employee.getPassword());
                preparedStatement.setDouble(5, employee.getSalary());
                preparedStatement.setString(6, employee.getAddress());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void updateEmployee(int employeeID, Employee updatedEmployee) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "UPDATE employees SET userID=?, email=?, password=?, salary=?, address=? WHERE employeeID=?")) {
    
                preparedStatement.setInt(1, updatedEmployee.getUserID());
                preparedStatement.setString(2, updatedEmployee.getEmail());
                preparedStatement.setString(3, updatedEmployee.getPassword());
                preparedStatement.setDouble(4, updatedEmployee.getSalary());
                preparedStatement.setString(5, updatedEmployee.getAddress());
                preparedStatement.setInt(6, employeeID);
    
                int rowsUpdated = preparedStatement.executeUpdate();
    
                if (rowsUpdated > 0) {
                    System.out.println("Employee with ID " + employeeID + " updated successfully!");
                } else {
                    System.out.println("Employee with ID " + employeeID + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteEmployee(int employeeID) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "DELETE FROM employees WHERE employeeID = ?")) {
    
                preparedStatement.setInt(1, employeeID);
                int rowsDeleted = preparedStatement.executeUpdate();
    
                if (rowsDeleted > 0) {
                    System.out.println("Employee with ID " + employeeID + " deleted successfully!");
                } else {
                    System.out.println("Employee with ID " + employeeID + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Employee getEmployeeById(int employeeID) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
    
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "SELECT * FROM employees WHERE employeeID = ?")) {
    
                preparedStatement.setInt(1, employeeID);
    
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Employee(
                                resultSet.getInt("userID"),
                                resultSet.getString("email"),
                                resultSet.getString("password"),
                                resultSet.getInt("employeeID"),
                                resultSet.getDouble("salary"),
                                resultSet.getString("address"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;
    }
    
    
    
    
    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean placeOrder(Order order) {//just for Status 
        if (order.status.equals("Pending")) {
            order.status = "Placed";
            return true;
        }
        return false;
    }
    
    public boolean cancelOrder(Order order) {//just for Status 
    	
        if (order.status.equals("Pending") || order.status.equals("Placed")) {
            order.status = "Cancelled";
            return true;
        }
        return false;
    }

    public boolean updateOrder(Order order) {//just for Status 
        if (order.status.equals("Pending") || order.status.equals("Placed")) {
            order.status = "Updated";
            return true;
        }
        return false;
    }


    public List<Order> viewPreviousSales() {
        return previousSales;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
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

        String jsonString = jsonText.toString();
        this.employeeID = Integer.parseInt(extractValue(jsonString, "employeeID"));
        this.salary = Double.parseDouble(extractValue(jsonString, "salary"));
        this.address = extractValue(jsonString, "address");
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

    public static void writeToJsonFileE(String fileName, List<Employee> employees) {//nabe nawaka wakw parent be loea lera E m la axere zyad krd (Mustafa)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < employees.size(); i++) {
                writer.write("    {\n");
                writer.write("        \"employeeID\": " + employees.get(i).employeeID + ",\n");
                writer.write("        \"salary\": " + employees.get(i).salary + ",\n");
                writer.write("        \"address\": \"" + employees.get(i).address + "\"\n");
                writer.write("    }");
                if (i < employees.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Employee data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String toString () {
    	return this.employeeID + " " + this.salary + " " + this.address + " " +this.previousSales + " " + this.roles ;  
    }


}
