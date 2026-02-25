package com.demo.notification_service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class OrderListener {

    @KafkaListener(topics = "order-topic")
    public void listen(Order order) {
        System.out.println("Received Order: " + order.product());
    }
}
