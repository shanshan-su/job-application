package com.personal.jobapplication.controllers;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import com.personal.jobapplication.services.Consumer;
import com.personal.jobapplication.services.Producer;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final Producer producer;
    private final Consumer consumer;
    private JobsRepository jobsDao;


    // generate a random int between 1 and 5
    public static int randomInt = new Random().nextInt(5) + 1;


    public KafkaController(Producer producer, Consumer consumer, JobsRepository jobsDao) {
        this.producer = producer;
        this.consumer = consumer;
        this.jobsDao = jobsDao;
    }

    // send Job objects to Kafka
    @PostMapping(value ="/post")
    public void sendJobToKafkaTopic(@RequestParam(name = "id") Long id) {
        Job job = jobsDao.getById(id);
        producer.publishJobToTopic(job);
    }

    // consuming Job objects from Kafka
    @GetMapping(value = "/in-progress")
    public void changeStatus(@RequestParam(name = "job") Job job) {
        producer.getJobKafkaTemplate().send("Kafka_jobs", job);
        jobsDao.save(job);
    }

    @GetMapping(value = "/produce/{id}")
    public void consumeJobComplete(@PathVariable Long id) throws InterruptedException {
        Job job = jobsDao.getById(id);

        // Job pass to Kafka if status is New, put it In-Progress(work on it)
        if (job.getStatus().equals("New")) {
            TimeUnit.MINUTES.sleep(randomInt);
            consumer.consumeJobFromTopic(job);
        }

        producer.getJobKafkaTemplate().send("Kafka_jobs", job);
    }
}
