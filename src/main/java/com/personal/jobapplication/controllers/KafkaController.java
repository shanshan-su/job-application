package com.personal.jobapplication.controllers;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import com.personal.jobapplication.services.Producer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
