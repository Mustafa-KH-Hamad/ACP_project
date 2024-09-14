import java.text.ParseException;
import java.util.*;

public interface ManagerActions {
    void addProduct(Product product);
    void removeProduct(Product product);
    //void editProductInfo(int productID, String newName, String newCategory, double newImportPrice, double newExportPrice, String newImportDate, String newExpireDate) throws ParseException;
    void addEmployee(Employee employee);
    void updateEmployeeInfo(Employee employee, double newSalary, String newAddress);
    void specifyRoles(Employee employee, List<String> roles);
    void makeAnnualReport();
    void configureSystem();
}
