package com.cartisan.gateway;

import com.cartisan.event.Event;
import com.google.gson.Gson;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author colin
 */
public class EventKafkaPublisher<T> implements EventPublisher {
    private final Destination destination;

    public EventKafkaPublisher(Destination destination) {
        this.destination = destination;
    }

    @Override
    public void publish(Event event) {
        final Producer<String, String> producer = createProducer();

        final ProducerRecord<String, String> producerRecord = new ProducerRecord<>(destination.topic(), new Gson().toJson(event));
        producer.send(producerRecord);
    }

    protected Producer<String, String> createProducer() {
        final Properties properties = buildProperties(destination);

        return new KafkaProducer<>(properties);
    }

    private Properties buildProperties(Destination destination) {
        final Properties properties = new Properties();
        properties.put("bootstrap.servers", destination.server());
        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 1);
        properties.put("buffer.memory", 33554432);
        return properties;
    }
}
