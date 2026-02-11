import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class IotDataIngestionService {

    @Value("${mqtt.broker.url}")
    private String mqttBrokerUrl;

    private MqttClient mqttClient;

    public void connect() throws MqttException {
        mqttClient = new MqttClient(mqttBrokerUrl, MqttClient.generateClientId());
        mqttClient.connect();
        mqttClient.subscribe("iot/devices/data", this::handleMessage);
    }

    private void handleMessage(String topic, MqttMessage message) {
        String payload = new String(message.getPayload());
        // Process the payload and validate it
        // Call validation and processing logic here
    }
}