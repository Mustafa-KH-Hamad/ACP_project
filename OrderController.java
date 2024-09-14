import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.*;

public class OrderController {
    Order order ;
    
    public OrderController() {
    }

    public Order addOrder(BufferedReader in, Employee employee, List<Product> products) throws ClassNotFoundException, SQLException {
        try {
           // Product product = new Product();
            int orderID = Integer.parseInt(in.readLine());
            //int employeeID = Integer.parseInt(in.readLine());//which employee you want to change
            String status = in.readLine();
            // int lengthOfProduct = Integer.parseInt(in.readLine());
            // ArrayList array = new ArrayList<>(lengthOfProduct);
            // for(int i = 0 ; i<lengthOfProduct ; i++){
            //     Scanner scan = new Scanner(System.in);
            //     System.out.println("Product id that you want to order : "+i);
            //     array.add(scan.nextInt());
            // } for(int i = 0 ; i<lengthOfProduct ; i++){
            //     Scanner scan = new Scanner(System.in);
            //     System.out.println("Product id that you want to order : "+i);
            //     products.add(product.getProductById(array.get(i)));
            // }
            Order order = new Order(orderID, employee , products, status);
            System.out.println(order.toString());
            return order;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Order updateOrder(BufferedReader in,Order order, Employee employee, List<Product> products) throws ClassNotFoundException, SQLException {
        try {
           // Product product = new Product();
            int orderID = Integer.parseInt(in.readLine());
            //int employeeID = Integer.parseInt(in.readLine());//which employee you want to change
            String status = in.readLine();
            // int lengthOfProduct = Integer.parseInt(in.readLine());
            // ArrayList array = new ArrayList<>(lengthOfProduct);
            // for(int i = 0 ; i<lengthOfProduct ; i++){
            //     Scanner scan = new Scanner(System.in);
            //     System.out.println("Product id that you want to order : "+i);
            //     array.add(scan.nextInt());
            // } for(int i = 0 ; i<lengthOfProduct ; i++){
            //     Scanner scan = new Scanner(System.in);
            //     System.out.println("Product id that you want to order : "+i);
            //     products.add(product.getProductById(array.get(i)));
            // }
            order.updateOrder(orderID, employee, products, status);
           return order ; 
               
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Order deletOrderRow(BufferedReader in,Order order){
        try {
            int orderID = Integer.parseInt(in.readLine());
            order.deleteOrder(orderID);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        return order;
    }


   
}

