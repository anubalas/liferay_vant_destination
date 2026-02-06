import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderController orderController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setName("Order 1");

        when(orderService.findAll()).thenReturn(Arrays.asList(order));

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Order 1"));
    }

    @Test
    public void testGetOrderById() throws Exception {
        Order order = new Order();
        order.setId(1L);
        order.setName("Order 1");

        when(orderService.findOne(1L)).thenReturn(Optional.of(order));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Order 1"));
    }

    @Test
    public void testCreateOrder() throws Exception {
        Order order = new Order();
        order.setName("Order 1");

        when(orderService.save(any(Order.class))).thenReturn(order);

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"Order 1\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Order 1"));
    }

    @Test
    public void testDeleteOrder() throws Exception {
        doNothing().when(orderService).delete(1L);

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isNoContent());
    }
}