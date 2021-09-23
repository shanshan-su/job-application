package com.personal.jobapplication.services;

import com.personal.jobapplication.models.Job;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    public static String topic = "Kafka_jobs";


    private KafkaTemplate<String, String> kafkaTemplate;
    private final KafkaTemplate<String, Job> jobKafkaTemplate;

    public Producer(KafkaTemplate<String, String> kafkaTemplate, KafkaTemplate<String, Job> jobKafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
        this.jobKafkaTemplate = jobKafkaTemplate;
    }

    // send String message to Kafka
    public void publishMessageToTopic(String message) {
        System.out.println("Publishing to topic: " + topic);
        this.kafkaTemplate.send(topic, message);
    }


    // send Job objects to Kafka
    public void publishJobToTopic(Job job) {
        System.out.printf("%s publishing to topic: %s%n", job.toString(), topic);
        this.jobKafkaTemplate.send(topic, job);
    }
}
