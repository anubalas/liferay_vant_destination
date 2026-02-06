import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository for managing Order entities.
 */
public class OrderRepository {
    private List<Order> orders = new ArrayList<>();

    /**
     * Finds an order by its ID.
     * @param id the ID of the order
     * @return an Optional containing the found order or empty if not found
     */
    public Optional<Order> findOne(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    /**
     * Finds all orders.
     * @return a list of all orders
     */
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    /**
     * Saves a new order or updates an existing one.
     * @param order the order to save
     */
    public void save(Order order) {
        orders.removeIf(o -> o.getId().equals(order.getId()));
        orders.add(order);
    }

    /**
     * Deletes an order by its ID.
     * @param id the ID of the order to delete
     */
    public void delete(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }
}