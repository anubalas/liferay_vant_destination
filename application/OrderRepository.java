import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Repository for managing Order entities.
 */
public class OrderRepository {
    private final Map<Long, Order> orderDatabase = new HashMap<>();
    private long currentId = 1;

    /**
     * Saves a new order to the repository.
     * @param order The order to save.
     * @return The saved order with generated id.
     */
    public Order save(Order order) {
        order.setId(currentId++);
        orderDatabase.put(order.getId(), order);
        return order;
    }

    /**
     * Finds an order by its id.
     * @param id The id of the order.
     * @return The order if found, null otherwise.
     */
    public Order findOne(Long id) {
        return orderDatabase.get(id);
    }

    /**
     * Retrieves all orders from the repository.
     * @return A list of all orders.
     */
    public List<Order> findAll() {
        return new ArrayList<>(orderDatabase.values());
    }

    /**
     * Deletes an order by its id.
     * @param id The id of the order to delete.
     */
    public void delete(Long id) {
        orderDatabase.remove(id);
    }
}