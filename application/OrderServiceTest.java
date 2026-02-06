import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.Optional;

public class OrderServiceTest {
    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        Order order1 = new Order();
        order1.setId(1L);
        order1.setName("Order 1");
        order1.setDescription("Description 1");

        when(orderRepository.findAll()).thenReturn(Arrays.asList(order1));

        List<Order> orders = orderService.findAll();
        assertEquals(1, orders.size());
        assertEquals("Order 1", orders.get(0).getName());
    }

    @Test
    public void testFindOne() {
        Order order = new Order();
        order.setId(1L);
        order.setName("Order 1");

        when(orderRepository.findById(1L)).thenReturn(Optional.of(order));

        Optional<Order> foundOrder = orderService.findOne(1L);
        assertTrue(foundOrder.isPresent());
        assertEquals("Order 1", foundOrder.get().getName());
    }

    @Test
    public void testSave() {
        Order order = new Order();
        order.setName("Order 1");

        when(orderRepository.save(order)).thenReturn(order);

        Order savedOrder = orderService.save(order);
        assertEquals("Order 1", savedOrder.getName());
    }

    @Test
    public void testDelete() {
        doNothing().when(orderRepository).deleteById(1L);
        orderService.delete(1L);
        verify(orderRepository, times(1)).deleteById(1L);
    }
}