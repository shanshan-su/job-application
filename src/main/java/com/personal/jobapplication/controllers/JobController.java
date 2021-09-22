package com.personal.jobapplication.controllers;

import com.personal.jobapplication.daos.JobsRepository;
import com.personal.jobapplication.models.Job;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class JobController {
    private JobsRepository jobsDao;

    public JobController(JobsRepository jobsDao) {
        this.jobsDao = jobsDao;
    }

    @GetMapping("/")
    public String getHomePage() {
        return "index";
    }

    @GetMapping("/jobs")
    @ResponseBody
    public ResponseEntity<List<Job>> getAllJobs() {
        List<Job> jobs = jobsDao.findAll();

        return ResponseEntity.ok(jobs);
    }

    @PostMapping("/jobs/create")
    @ResponseBody
    public ResponseEntity<Long> createJob(@RequestBody Job job) {
        jobsDao.save(job);

        return ResponseEntity.ok(job.getId());
    }

    @GetMapping("/jobs/{id}")
    @ResponseBody
    public ResponseEntity<String> individualJob(@PathVariable Long id) {
        Job job = jobsDao.getById(id);

        return ResponseEntity.ok(job.getStatus());
    }
}
