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
        Order order = new Order(null, "Test Order", "Description", LocalDateTime.now());
        orderRepository.save(order);
        Optional<Order> foundOrder = orderRepository.findOne(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals("Test Order", foundOrder.get().getName());
    }

    @Test
    public void testFindAll() {
        orderRepository.save(new Order(null, "Order 1", "Description 1", LocalDateTime.now()));
        orderRepository.save(new Order(null, "Order 2", "Description 2", LocalDateTime.now()));
        List<Order> orders = orderRepository.findAll();
        assertEquals(2, orders.size());
    }

    @Test
    public void testDelete() {
        Order order = new Order(null, "Order to Delete", "Description", LocalDateTime.now());
        orderRepository.save(order);
        orderRepository.delete(1L);
        Optional<Order> foundOrder = orderRepository.findOne(1L);
        assertFalse(foundOrder.isPresent());
    }
}