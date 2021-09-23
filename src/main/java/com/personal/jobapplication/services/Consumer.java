package com.personal.jobapplication.services;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Consumer {
    private JobsRepository jobsDao;

    public Consumer(JobsRepository jobsDao) {
        this.jobsDao = jobsDao;
    }


    @KafkaListener(topics = "Kafka_jobs", groupId = "jobs_json", containerFactory = "jobKafkaListenerContainerFactory")
    public void consumeJobFromTopic(Job job) {
        job.setStatus("Done");
        jobsDao.save(job);

        System.out.println("Consumed Job: " + job);
    }

}
