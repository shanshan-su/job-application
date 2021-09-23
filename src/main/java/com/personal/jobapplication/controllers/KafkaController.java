package com.personal.jobapplication.controllers;

import com.personal.jobapplication.models.Job;
import com.personal.jobapplication.services.Producer;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final Producer producer;

    public KafkaController(Producer producer) {
        this.producer = producer;
    }

    // send String message to Kafka
    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@RequestParam(name = "message") String message) {
        producer.publishMessageToTopic(message);
    }

    // send Job objects to Kafka
    @PostMapping(value="/post")
    public void sendJobToKafkaTopic(@RequestParam(name = "name") String name,
                                    @RequestParam(name = "details") String details) {
        Job job = new Job(name, "New", details);
        producer.publishJobToTopic(job);
    }

    // consume Job objects from Kafka
}
