import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;

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
        Order order = new Order(1L, "Test Order", "This is a test order", LocalDateTime.now());
        orderRepository.save(order);
        Optional<Order> foundOrder = orderRepository.findOne(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals(order.getName(), foundOrder.get().getName());
    }

    @Test
    public void testFindAll() {
        Order order1 = new Order(1L, "Order 1", "Description 1", LocalDateTime.now());
        Order order2 = new Order(2L, "Order 2", "Description 2", LocalDateTime.now());
        orderRepository.save(order1);
        orderRepository.save(order2);
        List<Order> orders = orderRepository.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    public void testDelete() {
        Order order = new Order(1L, "Order to delete", "Description", LocalDateTime.now());
        orderRepository.save(order);
        orderRepository.delete(1L);
        Optional<Order> foundOrder = orderRepository.findOne(1L);
        assertFalse(foundOrder.isPresent());
    }
}