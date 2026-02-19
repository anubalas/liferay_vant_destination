package com.example.iotmonitoring.config;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttAsyncClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for MQTT connection and message handling.
 */
@Configuration
public class MqttConfig {

    private static final String MQTT_BROKER_URL = "tcp://localhost:1883";
    private static final String CLIENT_ID = "IoTMonitoringClient";

    /**
     * Bean for MQTT client.
     *
     * @return MqttAsyncClient instance.
     * @throws MqttException if an error occurs during client creation.
     */
    @Bean
    public MqttAsyncClient mqttClient() throws MqttException {
        MqttAsyncClient client = new MqttAsyncClient(MQTT_BROKER_URL, CLIENT_ID);
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(true);
        client.connect(options);
        client.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                // Handle connection loss
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                // TODO: Implement message handling logic
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                // Handle delivery completion
            }
        });
        return client;
    }
}