import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ManagerController {
    

    public ManagerController() {
        
    }

    public Manager addManager(BufferedReader in,Manager manager) {
        try {
            int userID = Integer.parseInt(in.readLine());
            String email = in.readLine();
            String password = in.readLine();
            List<String> roles = new ArrayList<>();
            String role = in.readLine();
            roles.add(role);
                
            manager = new Manager(userID, email, password, roles);
            
//            System.out.println(manager.toString());doesnt need that (Mustafa)
            return manager;

        } catch (IOException e) {
        	
            e.printStackTrace();
            return null ;
        }
        
    }
    public Manager updateManager(BufferedReader in,Manager manager) {
        try {
            int userID = Integer.parseInt(in.readLine());
                String email = in.readLine();
                String password = in.readLine();
                List<String> roles = new ArrayList<>();
                String role = in.readLine();
                roles.add(role);
                manager.managerUpdate(userID,email,password,roles);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
        }

        return manager;
    }

     public Manager delteRow( BufferedReader in,Manager manager ) {
        try {
            int userID = Integer.parseInt(in.readLine());
            manager.deleteManager(userID);
        } catch (NumberFormatException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return manager;
     }

    public Manager addProduct( Manager manager , Product product) {
    	manager.addProduct(product);
    	return manager ;
    }
    public Manager removeProduct( Manager manager , Product product) {
    	manager.removeProduct(product);
    	return manager ;
    }
    // public Manager editProductInfo( Manager manager , Product product) throws ParseException {
    // 	 Date importDate = product.getImportDate();
    //      Date expireDate = product.getExpireDate();

         
    //      SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    //      String importDateString = dateFormat.format(importDate);
    //      String expireDateString = dateFormat.format(expireDate);
    //      manager.editProductInfo(product.getProductID(), product.getName(), product.getCategory(), product.getImportPrice(), product.getExportPrice(), importDateString, expireDateString);
    // 	return manager ;
    // }
    public Manager addEmployee( Manager manager , Employee employee) {
    	manager.addEmployee(employee);
    	return manager ;
    }//there  is two method needs to be done i think .
    
    public Manager editEmployeeInfo(BufferedReader in , Manager manager , Employee employee) throws IOException {
    	double newSalary = Double.parseDouble(in.readLine());
        String newAddress = in.readLine();
    	manager.updateEmployeeInfo(employee, newSalary, newAddress);
    	return manager ;
   }
    
}
