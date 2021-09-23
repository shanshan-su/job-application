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

    @PostMapping(value="/post")
    public void sendMessageToKafkaTopic(@RequestBody Job job) {
        producer.publishToTopic(job);
    }
}
