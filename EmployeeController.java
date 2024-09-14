import java.io.BufferedReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmployeeController {
    

    public EmployeeController() {
        
    }

    public Employee addEmployee(BufferedReader in , Employee employee) {
        try {
            int userID = Integer.parseInt(in.readLine());
            String email = in.readLine();
            String password = in.readLine();
            int employeeID = Integer.parseInt(in.readLine());
            double salary = Double.parseDouble(in.readLine());
            String address = in.readLine();

            employee = new Employee(userID, email, password, employeeID, salary, address);
            System.out.println(employee.toString());
            return employee;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        	}
    	}
    public Employee productUpdate(BufferedReader in, Employee employee , Product product) throws ParseException {
        try {
          

            int productID = Integer.parseInt(in.readLine());
            String name = in.readLine();
            String category = in.readLine();
            double importPrice = Double.parseDouble(in.readLine());
            double exportPrice = Double.parseDouble(in.readLine());


            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date importDate = dateFormat.parse(in.readLine());
            Date expireDate = dateFormat.parse(in.readLine());
            //kaka product daskare dakaen
            employee.productUpdate(product , productID, name, category, importPrice, exportPrice, importDate, expireDate);
            
            System.out.println(employee.toString());
            return employee;
            
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        	}
        }
    	public Employee placeOrder(BufferedReader in,Order order , Employee employee) {//just for status
			employee.placeOrder(order);
			System.out.println("Done");
    		return employee;
    	}
    	public Employee cancelOrder(BufferedReader in,Order order  , Employee employee) {//just for status
			employee.cancelOrder(order);
			System.out.println("Done");
    		return employee;
    	}public Employee updateOrderStatus(BufferedReader in,Order order  , Employee employee) {//just for status
			employee.updateOrder(order);
			System.out.println("Done");
    		return employee;
    	}
          public Employee EmployeeUpdateRow(BufferedReader in, Employee employee ){
            try {
                 int userID = Integer.parseInt(in.readLine());
            String email = in.readLine();
            String password = in.readLine();
            int employeeID = Integer.parseInt(in.readLine());
            double salary = Double.parseDouble(in.readLine());
            String address = in.readLine();
            employee.EmployeeUpdate(userID, email, password, employeeID, salary, address);
           
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return employee;
          }
          public Employee EmployeeDeleteRow(BufferedReader in, Employee employee ){
            try {
                int id = Integer.parseInt(in.readLine());
                employee.deleteEmployee(id);
            } catch (NumberFormatException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            return employee;
          }
          public Employee getEmployee(BufferedReader in, Employee employee  ){
            int id;
            try {
                id = Integer.parseInt(in.readLine());
                try {
                    employee.getEmployeeById(id);
                } catch (ClassNotFoundException e) {

                    e.printStackTrace();
                }
            } catch (NumberFormatException | IOException e) {
                e.printStackTrace();
            }
      
            return employee;
          }
    
    
}
