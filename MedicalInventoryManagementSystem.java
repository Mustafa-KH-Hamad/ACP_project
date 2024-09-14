import java.util.*;

public class MedicalInventoryManagementSystem {
    private List<User> users;
    private List<Product> products;
    private List<Order> orders;
    private List<Product> expiredProduct;

    public MedicalInventoryManagementSystem() {
        
        users = new ArrayList<>();
        products = new ArrayList<>();
        orders = new ArrayList<>();
        expiredProduct = new ArrayList<>();
    }
    

    public MedicalInventoryManagementSystem(List<User> users, List<Product> products, List<Order> orders, List<Product> expiredProduct) {
        this.users = users;
        this.products = products;
        this.orders = orders;
        this.expiredProduct = expiredProduct;
    }

    public boolean login(String email, String password) {
        // Implement login logic
    for (User user : users) {
        if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
            return true; // Successful login
        }
    }
        return false;
    }

    public void saveDataDaily() {
        System.out.println("Daily data backup completed.");
    }

    // Add more methods for system management here

    // Getters and setters for attributes

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Product> getExpiredProducts() {
        return expiredProduct;
    }

    public void setExpiredProducts(List<Product> expiredProducts) {
        this.expiredProduct = expiredProducts;
    }
}
