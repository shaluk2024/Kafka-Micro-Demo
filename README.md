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

=========================================================================
Kafka with spring boot locally without docker
=========================================================================
1. Download kafka - Apache kafka(binary)
2. Unzip the folder -> Rename the folder to kafka for conviniance.
3. Open a terminal -> Go to kafka folder and run commands:

   KAFKA_CLUSTER_ID="$(bin/kafka-storage.sh random-uuid)"

   bin/kafka-storage.sh format -t $KAFKA_CLUSTER_ID -c config/server.properties

4. Start Kafka ->  bin/kafka-server-start.sh config/server.properties

Now the kafka server is started locally on our system.
Now, We will create producer and consumer and topics.

Creating topic
===============================================
bin/kafka-topics.sh --create --topic order-topic --bootstrap-server localhost:9092 --partitions 1 --replication-factor 1

check topics -> bin/kafka-topics.sh --list --bootstrap-server localhost:9092

Producer
==============================================
Open new terminal, got to kafka folder and run the command

bin/kafka-console-producer.sh --topic demo-topic --bootstrap-server localhost:9092

Consumer
==============================================
Open new terminal, got to kafka folder and run the command

bin/kafka-console-consumer.sh --topic demo-topic --from-beginning --bootstrap-server localhost:9092

Now, start typing messages in producer terminal, you will see all of them in consumer terminal.

===========================================================================
# Connecting our spring boot application to the kafka server

1. Kafka is running at localhost:9091
2. Open order-service -> application.yaml, add this info(  kafka:
   bootstrap-servers: localhost:9092).
3. Same with notification service.
4. Run both the app separately. (mvn spring-boot:run)
5. Now, open postman. send a post request to http://localhost:8080/orders with body: {
   "id": 101,
   "product": "Bisleri",
   "price": 20
   }
6. Check notification service console.