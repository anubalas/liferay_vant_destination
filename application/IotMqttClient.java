import org.eclipse.paho.client.mqttv3.*;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

/**
 * MQTT Client for receiving messages from IoT devices.
 */
@Component
public class IotMqttClient implements MqttCallback {

    private MqttClient mqttClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.client.id}")
    private String clientId;

    /**
     * Initializes the MQTT client and connects to the broker.
     */
    @PostConstruct
    public void init() throws MqttException {
        mqttClient = new MqttClient(brokerUrl, clientId);
        mqttClient.setCallback(this);
        mqttClient.connect();
        mqttClient.subscribe("iot/data"); // Subscribe to the topic for IoT data
    }

    /**
     * Callback method for handling incoming messages.
     * @param topic The topic on which the message was received.
     * @param message The message payload.
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        // Validate and process the incoming message
        String payload = new String(message.getPayload());
        if (isValidJson(payload)) {
            // Process valid JSON data
            System.out.println("Received valid message: " + payload);
        } else {
            // Log error for invalid format
            System.err.println("Received invalid message format: " + payload);
        }
    }

    /**
     * Validates if the incoming payload is a valid JSON format.
     * @param payload The message payload to validate.
     * @return true if valid JSON, false otherwise.
     */
    private boolean isValidJson(String payload) {
        try {
            objectMapper.readTree(payload);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Disconnects the MQTT client when the application is stopped.
     */
    @PreDestroy
    public void cleanup() throws MqttException {
        if (mqttClient != null && mqttClient.isConnected()) {
            mqttClient.disconnect();
        }
    }
}
