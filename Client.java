import java.io.*;
import java.net.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) {
		try (Socket socket = new Socket("localhost", 1234)) {
			try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

				while (true) {
					Scanner sc = new Scanner(System.in);
					System.out.println("""
							slaw
							1 Add Product
							2 update Product
							3 Add Employee
							4 Update Product by Employee
							5 Add order
							6 update order
							7 place order by Employee
							8 Cancel order by Employee
							9 update order status by Employee
							10 Add Manager
							11 ADD Product by Manager
							12 Remove Product by Manager
							13 Edit Product info by Manager
							14 Add Employee By Manager
							15 Edit Employee By Manager
							16 Get Product
							17 Delete Product row
							18	Update Employee
							19	Delete Employee row
							20	Get Employee row
							21  Delete order row
							22 update manager
							23 delete manager
							0 Quit
							-1 close the server
							""");

					int y = sc.nextInt();
					if (y == 1) {

						out.println("ADD_PRODUCT");
						// Use Scanner to get user input
						try {
							Scanner productScanner = new Scanner(System.in);

							System.out.println("Enter product code:");
							String productCode = productScanner.next();
							out.println(productCode);

							System.out.println("Enter product name:");
							String productName = productScanner.next();
							out.println(productName);

							System.out.println("Enter product description:");
							String productDescription = productScanner.next();
							out.println(productDescription);

							System.out.println("Enter product price:");
							double productPrice = productScanner.nextDouble();
							out.println(productPrice);

							System.out.println("Enter product discount:");
							double productDiscount = productScanner.nextDouble();
							out.println(productDiscount);

							System.out.println("Enter start date (yyyy-MM-dd):");
							String startDate = productScanner.next();
							out.println(startDate);

							System.out.println("Enter end date (yyyy-MM-dd):");
							String endDate = productScanner.next();
							out.println(endDate);

						} catch (NumberFormatException e) {
							System.out.println("All datas should be String Detail : " + e.toString());
						} catch (InputMismatchException err) {
							System.out.println(
									"The data should be in the right format \n id name catagory inportprice exportprice inportdate export date");
						}
						Object response = in.readLine();
						System.out.println("Server response: " + response);

					} else if (y == 2) {
						out.println("UPDATE_PRODUCT");
						out.println("111");
						out.println("TEST");
						out.println("TEST");
						out.println("10.0");
						out.println("20.0");
						out.println("2023-11-06");
						out.println("2024-11-06");
						Object response = in.readLine();
						System.out.println("Server response: " + response);

					} else if (y == 3) {

						out.println("ADD_EMPLOYEE");
						Scanner employeeScanner = new Scanner(System.in);

						System.out.println("Enter user id:");
						String userId = employeeScanner.next();
						out.println(userId);

						System.out.println("Enter email:");
						String email = employeeScanner.next();
						out.println(email);

						System.out.println("Enter password:");
						String password = employeeScanner.next();
						out.println(password);

						System.out.println("Enter employee id:");
						String employeeId = employeeScanner.next();
						out.println(employeeId);

						System.out.println("Enter salary:");
						String salary = employeeScanner.next();
						out.println(salary);

						System.out.println("Enter address:");
						String address = employeeScanner.next();
						out.println(address);

						Object response = in.readLine();
						System.out.println("Server response: " + response);
					}
					if (y == 4) {

						out.println("UPDATEPRODUCT_EMPLOYEE");
						out.println("1");
						out.println("Product Name");
						out.println("Product Category");
						out.println("10.0");
						out.println("20.0");
						out.println("2023-11-06");
						out.println("2024-11-06");
						Object response = in.readLine();
						System.out.println("Server response: " + response);

					} else if (y == 5) {

						out.println("ADD_ORDER");
						Scanner scan = new Scanner(System.in);
						System.out.println("order id ");
						out.println(scan.next());
						System.out.println("Status Pending for e.g.");
						out.println(scan.next());

						// Scanner scan = new Scanner(System.in);
						// System.out.println("how many product do you order ");
						// out.println(scan.next());

						Object response = in.readLine();
						System.out.println("Server response: " + response);

					} else if (y == 6) {
						out.println("UPDATE_ORDER");

						Scanner scan = new Scanner(System.in);
						System.out.println("which order do you want to change");
						out.println(scan.next());
						System.out.println("Status Pending for e.g.");
						out.println(scan.next());
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 7) {
						out.println("PLACAEORDER_EMPLOYEE");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 8) {
						out.println("CANCELORDER_EMPLOYEE");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 10) {
						out.println("ADD_MANAGER");
						out.println("1010");
						out.println("Manager@example.com");
						out.println("password1");
						out.println("TOP MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 11) {
						out.println("ADDPRODUCT_MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 12) {
						out.println("REMOVEPRODUCT_MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 13) {
						out.println("EDITPRODUCTINFO_MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 14) {
						out.println("ADDEMPLOYEE_MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 15) {
						out.println("EDITEMPLOYEEINFO_MANAGER");
						out.println("11.111");
						out.println("newAddress");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 16) {
						out.println("GET_PRODUCT");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the proudct you want to change ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 17) {
						out.println("DELETEROW_PRODUCT");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the proudct you want to change ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 18) {
						out.println("UPDATEROW_EMPLOYEE");

						Scanner employeeScanner = new Scanner(System.in);

						System.out.println(
								"Enter employee id you want to change order id and employee id should be the same :");
						String userId = employeeScanner.next();
						out.println(userId);

						System.out.println("Enter new email:");
						String email = employeeScanner.next();
						out.println(email);

						System.out.println("Enter new password:");
						String password = employeeScanner.next();
						out.println(password);

						System.out.println("Enter new employee id:");
						String employeeId = employeeScanner.next();
						out.println(employeeId);

						System.out.println("Enter new salary:");
						String salary = employeeScanner.next();
						out.println(salary);

						System.out.println("Enter new address:");
						String address = employeeScanner.next();
						out.println(address);
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 19) {
						out.println("DELETEROW_EMPLOYEE");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the emplyee you want to change ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 20) {
						out.println("GET_EMPLOYEE");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the emplyee you want to GET ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 21) {
						out.println("DELETEROW_ORDER");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the emplyee you want to change ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					}else if (y == 22) {
						out.println("UPDATE_MANAGER");
						out.println("101");
						out.println("Manager@example.com");
						out.println("password1");
						out.println("TOP MANAGER");
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					} else if (y == 23) {
						out.println("DELETEROW_MANAGER");
						Scanner scanner = new Scanner(System.in);
						System.out.println("Enter id of the emplyee you want to change ");
						out.println(scanner.nextLine());// id to return the row (Mustafa)
						Object response = in.readLine();
						System.out.println("Server response: " + response);
					}
					 else if (y == -1) {
						out.println("CLOSE");
						System.out.println("Server response: bye bye  Client  closed the Server ");
						break;
					}

					else if (y == 0) {

						System.out.println("Server response: bye bye ");
						sc.close();
						break;

					}
				}

			}

		} catch (IOException e) {
			System.out.println("Client error");
			e.printStackTrace();
		}
	}
}
