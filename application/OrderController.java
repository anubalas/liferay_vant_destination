import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * Controller for handling Order-related requests.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderRepository orderRepository = new OrderRepository();

    /**
     * Gets all orders.
     * @return a list of all orders
     */
    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Gets a specific order by ID.
     * @param id the ID of the order
     * @return the order if found
     */
    @GetMapping("/{id}")
    public Optional<Order> getOrder(@PathVariable Long id) {
        return orderRepository.findOne(id);
    }

    /**
     * Creates a new order.
     * @param order the order to create
     */
    @PostMapping
    public void createOrder(@RequestBody Order order) {
        orderRepository.save(order);
    }

    /**
     * Deletes an order by ID.
     * @param id the ID of the order to delete
     */
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderRepository.delete(id);
    }
}