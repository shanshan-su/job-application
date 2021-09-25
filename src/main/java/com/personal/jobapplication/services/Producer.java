package com.personal.jobapplication.services;

import com.personal.jobapplication.models.Job;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    public static String topic = "Kafka_jobs";

    private final KafkaTemplate<String, Job> jobKafkaTemplate;

    public Producer( KafkaTemplate<String, Job> jobKafkaTemplate) {
        this.jobKafkaTemplate = jobKafkaTemplate;
    }

    public KafkaTemplate<String, Job> getJobKafkaTemplate() {
        return jobKafkaTemplate;
    }

    // send Job objects to Kafka
    public void publishJobToTopic(Job job) {
        System.out.printf("%s publishing to topic: %s%n", job.toString(), topic);
        this.jobKafkaTemplate.send(topic, job);
    }
}
