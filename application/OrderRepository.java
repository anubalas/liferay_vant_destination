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
     */
    public Optional<Order> findOne(Long id) {
        return orders.stream().filter(order -> order.getId().equals(id)).findFirst();
    }

    /**
     * Finds all orders.
     */
    public List<Order> findAll() {
        return new ArrayList<>(orders);
    }

    /**
     * Saves a new order or updates an existing one.
     */
    public void save(Order order) {
        orders.removeIf(o -> o.getId().equals(order.getId()));
        orders.add(order);
    }

    /**
     * Deletes an order by its ID.
     */
    public void delete(Long id) {
        orders.removeIf(order -> order.getId().equals(id));
    }
}