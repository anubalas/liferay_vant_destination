package com.example.iotmonitoring.service;

import org.eclipse.paho.client.mqttv3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Service for handling data ingestion via MQTT.
 */
@Service
public class MqttDataIngestionService implements MqttCallback {

    private final ConcurrentLinkedQueue<String> dataQueue = new ConcurrentLinkedQueue<>();

    @Value("${data.ingestion.mqtt.interval}")
    private long ingestionInterval;

    private MqttClient client;

    /**
     * Initializes the MQTT client and subscribes to topics.
     */
    @PostConstruct
    public void init() throws MqttException {
        client = new MqttClient("tcp://broker.hivemq.com:1883", MqttClient.generateClientId());
        client.setCallback(this);
        client.connect();
        client.subscribe("iot/data");
    }

    /**
     * Processes incoming MQTT messages.
     * @param topic the topic of the message.
     * @param message the message received.
     */
    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        String data = new String(message.getPayload());
        if (isValidJson(data)) {
            dataQueue.add(data);
            // Logic to handle data storage and processing
        } else {
            throw new CustomException("Invalid data format");
        }
    }

    /**
     * Validates the incoming JSON data format.
     * @param data JSON data as a string.
     * @return true if valid, false otherwise.
     */
    private boolean isValidJson(String data) {
        // Implement JSON validation logic
        return true;
    }
}