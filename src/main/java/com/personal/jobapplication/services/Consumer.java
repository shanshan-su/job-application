package com.personal.jobapplication.services;

import com.personal.jobapplication.models.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

    @KafkaListener(topics = "Kafka_test", groupId = "group_id")
    public void consumeMessageFromTopic(String message) {
        System.out.println("Consumed message: " + message);
    }

    @KafkaListener(topics = "Kafka_jobs", groupId = "jobs_json", containerFactory = "jobKafkaListenerContainerFactory")
    public void consumeJobFromTopic(Job job) {
        System.out.println("Consumed Job: " + job);
    }
}
