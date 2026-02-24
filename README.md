# Kafka-Micro-Demo

Kafka-Micro-Demo is a simple event-driven microservices project built
using Spring Boot, Apache Kafka, and Docker.

It demonstrates asynchronous communication between two services using
Kafka.

------------------------------------------------------------------------

## Architecture

order-service → Kafka → notification-service

-   order-service publishes an Order event to Kafka.
-   Kafka stores the event in a topic.
-   notification-service consumes the event.

------------------------------------------------------------------------

## Tech Stack

-   Spring Boot
-   Spring Kafka
-   Apache Kafka
-   Docker & Docker Compose
-   Maven

------------------------------------------------------------------------

## How to Run

From the project root:

docker-compose down -v 
docker-compose up --build

This starts: - Zookeeper - Kafka - Order Service (8080) - Notification
Service (8081)

------------------------------------------------------------------------

## Kafka Configuration

Inside services:

spring: kafka: bootstrap-servers: kafka:9092

Inside Docker:

KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092

Note: When running inside Docker, use kafka:9092, not localhost:9092.

------------------------------------------------------------------------

## Event Flow

Producer (order-service):

kafkaTemplate.send("order-created", order);

Consumer (notification-service):

@KafkaListener(topics = "order-created", groupId = "notify-group") public
void listen(Order order) { log.info("Received Order:", order.getProduct()); }

------------------------------------------------------------------------

## Purpose

This project demonstrates: - Kafka producer and consumer setup -
Event-driven microservices communication - Docker-based local setup -
Proper Kafka networking configuration