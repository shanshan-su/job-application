package com.personal.jobapplication.controllers;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {
    private final JobsRepository jobsDao;

    public JobController(JobsRepository jobsDao) {
        this.jobsDao = jobsDao;
    }


    @GetMapping("/jobs")
    @ResponseBody
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobsDao.findAll();

        return ResponseEntity.ok(jobs);
    }

    @GetMapping("/jobs/{id}")
    @ResponseBody
    public ResponseEntity<Job> individualJob(@PathVariable Long id) {
        Job job = jobsDao.getById(id);

        return ResponseEntity.ok(job);
    }
}
