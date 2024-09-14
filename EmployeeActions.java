import java.util.List;

public interface EmployeeActions {
    boolean placeOrder(Order order);
    boolean cancelOrder(Order order);
    boolean updateOrder(Order order);
    List<Order> viewPreviousSales();
}
