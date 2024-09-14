import java.io.*;
import java.net.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static List<Product> products = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1234);
            System.out.println("Server running on port 1234");

            Socket clientSocket = serverSocket.accept();
            new ServerThread(clientSocket).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class ServerThread extends Thread {
    private Socket clientSocket;
    Product product;
    Employee employee;
    Order order;
    Manager manager;

    public ServerThread(Socket clientSocket) {
        this.clientSocket = clientSocket;

    }

    @Override
    public void run() {
        System.out.println("Connected");

        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

            while (true) {
                String request = in.readLine();

                Controller controller = new Controller();// product
                EmployeeController empController = new EmployeeController();
                ManagerController mangcon = new ManagerController();
                OrderController orderCon = new OrderController();

                if (request != null) {
                    if ("ADD_EMPLOYEE".equals(request)) {
                        Employee response = empController.addEmployee(in, employee);
                        out.println(response);
                        employee = response;
                    } else if ("UPDATEPRODUCT_EMPLOYEE".equals(request)) {
                        // check database if there was a product then you can change it when we reach DB
                        Employee response = empController.productUpdate(in, employee, product);
                        out.println(response);
                        employee = response;
                    } else if ("PLACAEORDER_EMPLOYEE".equals(request)) {
                        Employee response = empController.placeOrder(in, order, employee);
                        out.println(response);
                        employee = response;
                    } else if ("CANCELORDER_EMPLOYEE".equals(request)) {
                        Employee response = empController.cancelOrder(in, order, employee);
                        out.println(response);
                        employee = response;
                    } else if ("UPDATEORDERSTATUS_EMPLOYEE".equals(request)) {
                        Employee response = empController.updateOrderStatus(in, order, employee);
                        out.println(response);
                        employee = response;
                    } else if ("UPDATEROW_EMPLOYEE".equals(request)) {
                        Employee response = empController.EmployeeUpdateRow(in, employee);
                        out.println(response);
                        employee = response;
                    } else if ("DELETEROW_EMPLOYEE".equals(request)) {
                        Employee response = empController.EmployeeDeleteRow(in, employee);
                        out.println(response);
                        employee = response;
                    } else if ("GET_EMPLOYEE".equals(request)) {
                        Employee response = empController.getEmployee(in, employee);
                        out.println(response);
                        employee = response;
                    } else if ("ADD_PRODUCT".equals(request)) {
                        Product response = controller.addProduct(in, product);
                        out.println(response);
                        product = response;
                    } else if ("GET_PRODUCT".equals(request)) {
                        Product response = controller.getProduct(in, product);
                        out.println(response);
                        product = response;
                    } else if ("UPDATE_PRODUCT".equals(request)) {
                        Product response = controller.updateProduct(in, product);
                        out.println(response);
                        product = response;
                    } else if ("DELETEROW_PRODUCT".equals(request)) {
                        Product response = controller.deleteProduct(in, product);
                        out.println(response);
                        product = response;
                    } else if ("ADD_MANAGER".equals(request)) {
                        Manager response = mangcon.addManager(in, manager);
                        out.println(response);
                        manager = response;
                    } else if ("UPDATE_MANAGER".equals(request)) {
                        Manager response = mangcon.updateManager(in, manager);
                        out.println(response);
                        manager = response;
                    } else if ("DELETEROW_MANAGER".equals(request)) {
                        Manager response = mangcon.delteRow(in, manager);
                        out.println(response);
                        manager = response;
                    }
                     else if ("ADD_ORDER".equals(request)) {
                        ArrayList<Product> products = new ArrayList();
                        products.add(product);
                        Order response = orderCon.addOrder(in, employee, products);
                        out.println(response);
                        order = response;
                    } else if ("UPDATE_ORDER".equals(request)) {
                        ArrayList<Product> products = new ArrayList();
                        products.add(product);
                        Order response = orderCon.updateOrder(in,order, employee, products);
                        out.println(response);
                        order = response;
                    }else if ("DELETEROW_ORDER".equals(request)) {
                        Order response = orderCon.deletOrderRow(in,order);
                        out.println(response);
                        order = response;
                    } else if ("ADD_MANAGER".equals(request)) {
                        Manager response = mangcon.addManager(in, manager);
                        out.println(response);
                        manager = response;
                    } else if ("ADDPRODUCT_MANAGER".equals(request)) {
                        Manager response = mangcon.addProduct(manager, product);
                        out.println(response);
                        manager = response;
                    } else if ("REMOVEPRODUCT_MANAGER".equals(request)) {
                        Manager response = mangcon.removeProduct(manager, product);
                        out.println(response);
                        manager = response;
                     }
                    // else if ("EDITPRODUCTINFO_MANAGER".equals(request)) {
                    //     Manager response = mangcon.editProductInfo(manager, product);
                    //     out.println(response);
                    //     manager = response;
                    // } 
                    else if ("ADDEMPLOYEE_MANAGER".equals(request)) {
                        Manager response = mangcon.addEmployee(manager, employee);
                        out.println(response);
                        manager = response;
                    } else if ("EDITPRODUCTINFO_MANAGER".equals(request)) {
                        Manager response = mangcon.editEmployeeInfo(in, manager, employee);
                        out.println(response);
                        manager = response;
                    }else if ("CLOSE".equals(request)) {
                        System.out.println("Server closed");
                        break;
                    } else {
                        System.out.println("Request Ended");
                        out.println("Invalid request.");
                        break;
                    }
                }
            }
        } catch (IOException | ParseException | SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
