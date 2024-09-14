import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Controller {
    public Controller() {
    }

    public Product addProduct(BufferedReader in, Product product) throws ParseException, SQLException, ClassNotFoundException {
        try {
        	
           

            int productID = Integer.parseInt(in.readLine());
            String name = in.readLine();
            String category = in.readLine();
            double importPrice = Double.parseDouble(in.readLine());
            double exportPrice = Double.parseDouble(in.readLine());


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date importDate = dateFormat.parse(in.readLine());
            Date expireDate = dateFormat.parse(in.readLine());

            
            product = new Product(productID ,name, category, importPrice, exportPrice, importDate, expireDate);
            product.insertProduct(product);
            
            System.out.println(product.toString());
            return product;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    } 
    public Product updateProduct(BufferedReader in, Product product) throws ParseException {
        try {
        	

            int productID = Integer.parseInt(in.readLine());
            String name = in.readLine();
            String category = in.readLine();
            double importPrice = Double.parseDouble(in.readLine());
            double exportPrice = Double.parseDouble(in.readLine());


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date importDate = dateFormat.parse(in.readLine());
            Date expireDate = dateFormat.parse(in.readLine());

            product.productUpdate(productID, name, category, importPrice, exportPrice, importDate, expireDate);
            System.out.println(product.toString());
            return product;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public Product getProduct(BufferedReader in,Product product) throws ClassNotFoundException {
        try {
           int id = Integer.parseInt(in.readLine());
           	product.getProductById(id);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
    
    	return product;
    }
    public Product deleteProduct(BufferedReader in,Product product ){
        try {
          int  id = Integer.parseInt(in.readLine());
          product.deleteProduct(id);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }
        
        return product;
    }
    


    
}
