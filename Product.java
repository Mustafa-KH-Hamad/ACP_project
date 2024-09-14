import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.beans.Statement;
import java.io.*;
import java.sql.*;

public class Product {
    private int productID;
    private String name;
    private String category;
    private double importPrice;
    private double exportPrice;
    private Date importDate;
    private Date expireDate;
    // static String url ;
    // static String username ;
    // static String password;

    public Product() throws SQLException, ClassNotFoundException {
        createTable();
    }

    public Product(int productID, String name, String category, double importPrice, double exportPrice, Date importDate,
            Date expireDate) throws SQLException, ClassNotFoundException {
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.importDate = importDate;
        this.expireDate = expireDate;
        createTable();
        insertProduct(this);
        if (isExpired()) {
            System.out.println("Be aware This product is expired");
        }

    }

    public static void createTable() {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement smt = connection.prepareStatement("CREATE TABLE IF NOT EXISTS products (" +
                            "productID INT PRIMARY KEY," +
                            "name VARCHAR(255)," +
                            "category VARCHAR(255)," +
                            "importPrice DOUBLE," +
                            "exportPrice DOUBLE," +
                            "importDate DATE," +
                            "expireDate DATE)")) {

                int i = smt.executeUpdate();
                if (i == 0) {
                    System.out.println("Table already exists! Data saved ! ");
                } else {
                    System.out.println("Table created successfully!");
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); // Handle the ClassNotFoundException appropriately
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the SQLException appropriately
        }
    }

    public void productUpdate(int productID, String name, String category, double importPrice, double exportPrice,
            Date importDate, Date expireDate) {
        this.productID = productID;
        this.name = name;
        this.category = category;
        this.importPrice = importPrice;
        this.exportPrice = exportPrice;
        this.importDate = importDate;
        this.expireDate = expireDate;
        createTable();
        updateProduct(productID, this);
        if (isExpired()) {
            System.out.println("Be aware This product is expired");
        }
    }

    public static void insertProduct(Product product) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "INSERT INTO products VALUES (?, ?, ?, ?, ?, ?, ?)")) {

                preparedStatement.setInt(1, product.getProductID());
                preparedStatement.setString(2, product.getName());
                preparedStatement.setString(3, product.getCategory());
                preparedStatement.setDouble(4, product.getImportPrice());
                preparedStatement.setDouble(5, product.getExportPrice());
                preparedStatement.setDate(6, new java.sql.Date(product.getImportDate().getTime()));
                preparedStatement.setDate(7, new java.sql.Date(product.getExpireDate().getTime()));
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct(int productId) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "DELETE FROM products WHERE productID = ?")) {

                preparedStatement.setInt(1, productId);
                int rowsDeleted = preparedStatement.executeUpdate();

                if (rowsDeleted > 0) {
                    System.out.println("Product deleted successfully!");
                } else {
                    System.out.println("Product with ID " + productId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Product getProductById(int productId) throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(url, username, password);
                PreparedStatement preparedStatement = connection.prepareStatement(
                        "SELECT * FROM products WHERE productID = ?")) {
            preparedStatement.setInt(1, productId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println(resultSet.getInt("productID"));
                    System.out.println(resultSet.getString("name"));
                    System.out.println(resultSet.getString("category"));
                    System.out.println(resultSet.getDouble("importPrice"));
                    System.out.println(resultSet.getDouble("exportPrice"));
                    System.out.println(resultSet.getDate("importDate"));
                    System.out.println(resultSet.getDate("expireDate"));
                    return new Product(
                            resultSet.getInt("productID"),
                            resultSet.getString("name"),
                            resultSet.getString("category"),
                            resultSet.getDouble("importPrice"),
                            resultSet.getDouble("exportPrice"),
                            resultSet.getDate("importDate"),
                            resultSet.getDate("expireDate"));

                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void updateProduct(int productId, Product updatedProduct) {
        String url = "jdbc:mysql://localhost:3306/medical_inventory_management_system";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            try (Connection connection = DriverManager.getConnection(url, username, password);
                    PreparedStatement preparedStatement = connection.prepareStatement(
                            "UPDATE products SET name=?, category=?, importPrice=?, exportPrice=?, importDate=?, expireDate=? WHERE productID=?")) {

                preparedStatement.setString(1, updatedProduct.getName());
                preparedStatement.setString(2, updatedProduct.getCategory());
                preparedStatement.setDouble(3, updatedProduct.getImportPrice());
                preparedStatement.setDouble(4, updatedProduct.getExportPrice());
                preparedStatement.setDate(5, new java.sql.Date(updatedProduct.getImportDate().getTime()));
                preparedStatement.setDate(6, new java.sql.Date(updatedProduct.getExpireDate().getTime()));
                preparedStatement.setInt(7, productId);

                int rowsUpdated = preparedStatement.executeUpdate();

                if (rowsUpdated > 0) {
                    System.out.println("Product with ID " + productId + " updated successfully!");
                } else {
                    System.out.println("Product with ID " + productId + " not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(double importPrice) {
        this.importPrice = importPrice;
    }

    public double getExportPrice() {
        return exportPrice;
    }

    public void setExportPrice(double exportPrice) {
        this.exportPrice = exportPrice;
    }

    public Date getImportDate() {
        return importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public void readFromJsonFile(String fileName) {// using this method helps reading JSON file for Product which all
                                                   // the Data from JSON will return and
        // will be used for the variables in Product Class then in the Main we will call
        // toString or specific setMethod as we want (Mustafa)
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
        this.name = extractValue(jsonString, "name");
        this.productID = Integer.parseInt(extractValue(jsonString, "productID"));
        this.category = extractValue(jsonString, "category");
        this.importPrice = Double.parseDouble(extractValue(jsonString, "importPrice"));
        this.exportPrice = Double.parseDouble(extractValue(jsonString, "exportPrice"));

        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        try {
            this.importDate = dateFormat.parse(extractValue(jsonString, "importDate"));
            this.expireDate = dateFormat.parse(extractValue(jsonString, "expireDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
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

    public static void writeToJsonFile(String fileName, List<Product> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("[\n");
            for (int i = 0; i < products.size(); i++) {
                writer.write("    {\n");
                writer.write("        \"productID\": " + products.get(i).productID + ",\n");
                writer.write("        \"name\": \"" + products.get(i).name + "\",\n");
                writer.write("        \"category\": \"" + products.get(i).category + "\",\n");
                writer.write("        \"importPrice\": " + products.get(i).importPrice + ",\n");
                writer.write("        \"exportPrice\": " + products.get(i).exportPrice + ",\n");
                writer.write("        \"importDate\": \"" + products.get(i).importDate + "\",\n");
                writer.write("        \"expireDate\": \"" + products.get(i).expireDate + "\"\n");
                writer.write("    }");
                if (i < products.size() - 1) {
                    writer.write(",");
                }
                writer.write("\n");
            }
            writer.write("]");
            System.out.println("Product data has been written to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String toString() {
        return "Product ID: " + productID + "\nName: " + name + "\nCategory: " + category + "\nImport Price: "
                + importPrice + "\nExport Price: " + exportPrice + "\nImport Date: " + importDate + "\nExpire Date: "
                + expireDate;
    }

    public boolean isExpired() { // checking to see if its Expired or not (Mustafa)
        Date currentDate = new Date();
        return expireDate.before(currentDate);
    }

    public Product getProductById(Object object) {
        return null;
    }
}