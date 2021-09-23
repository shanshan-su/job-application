package com.personal.jobapplication.services;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class Consumer {
    private JobsRepository jobsDao;

    public Consumer(JobsRepository jobsDao) {
        this.jobsDao = jobsDao;
    }


    @KafkaListener(topics = "Kafka_jobs", groupId = "jobs_json", containerFactory = "jobKafkaListenerContainerFactory")
    public void consumeJobFromTopic(Job job) throws InterruptedException {

        // generate a random int between 1 and 5
        int randomInt = new Random().nextInt(5) + 1;


        // if job status is new, consume it
        if (job.getStatus().equalsIgnoreCase("New")) {
            // change status to In-Progress
            job.setStatus("In-Progress");
            jobsDao.save(job);

            // put the application to sleep(mimic working on job)
            TimeUnit.MINUTES.sleep(randomInt);
            job.setStatus("Done");
            jobsDao.save(job);

            System.out.println("Consumed Job: " + job);
        } else if (job.getStatus().equalsIgnoreCase("In-Progress")) {
            System.out.println("Consuming Job: " + job);
            // put the application to sleep(mimic working on job)
            TimeUnit.MINUTES.sleep(randomInt);
            job.setStatus("Done");
            jobsDao.save(job);
            System.out.println("Consumed Job: " + job);
        } else {
            System.out.println("Job is done, no need to consume.");
        }
    }

}
