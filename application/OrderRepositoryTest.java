import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * Unit tests for OrderRepository.
 */
public class OrderRepositoryTest {
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        orderRepository = new OrderRepository();
    }

    @Test
    public void testSaveAndFindOne() {
        Order order = new Order(null, "Test Order", "This is a test order.");
        Order savedOrder = orderRepository.save(order);
        Order foundOrder = orderRepository.findOne(savedOrder.getId());
        assertNotNull(foundOrder);
        assertEquals(savedOrder.getId(), foundOrder.getId());
    }

    @Test
    public void testFindAll() {
        orderRepository.save(new Order(null, "Order 1", "Description 1"));
        orderRepository.save(new Order(null, "Order 2", "Description 2"));
        List<Order> orders = orderRepository.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    public void testDelete() {
        Order order = new Order(null, "Order to Delete", "This order will be deleted.");
        Order savedOrder = orderRepository.save(order);
        orderRepository.delete(savedOrder.getId());
        assertNull(orderRepository.findOne(savedOrder.getId()));
    }

    @Test
    public void testFindOneNotFound() {
        assertNull(orderRepository.findOne(999L)); // Non-existing ID
    }
}