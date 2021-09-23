package com.personal.jobapplication.controllers;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import com.personal.jobapplication.services.Consumer;
import com.personal.jobapplication.services.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final Producer producer;
    private final JobsRepository jobsDao;



    public KafkaController(Producer producer, JobsRepository jobsDao) {
        this.producer = producer;
        this.jobsDao = jobsDao;
    }

    // send Job objects to Kafka
    @PostMapping(value ="/create-job")
    public ResponseEntity<Long> sendJobToKafkaTopic(@RequestParam(name = "name") String name,
                                    @RequestParam(name = "details") String details) {
        // create the job object and then save to db
        Job job = new Job(name, "New", details);
        jobsDao.save(job);

        // send job to topic
        producer.publishJobToTopic(job);

        // return job id
        return ResponseEntity.ok(job.getId());

    }


//    @GetMapping(value = "/in-progress")
//    public void changeStatus(@RequestParam(name = "job") Job job) {
//        producer.getJobKafkaTemplate().send("Kafka_jobs", job);
//        jobsDao.save(job);
//    }

    // consuming Job objects from Kafka
    @GetMapping(value = "/produce/{id}")
    public void consumeJob(@PathVariable Long id) throws InterruptedException {
        Job job = jobsDao.getById(id);



        producer.getJobKafkaTemplate().send("Kafka_jobs", job);
    }
}
