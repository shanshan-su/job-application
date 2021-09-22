package com.codeup.jobapplication.controllers;

import com.codeup.jobapplication.daos.JobsRepository;
import com.codeup.jobapplication.models.Job;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class JobController {
    private JobsRepository jobsDao;

    public JobController(JobsRepository jobsDao) {
        this.jobsDao = jobsDao;
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "index";
    }

    @PostMapping("/jobs/create")
    public String createJob(@ModelAttribute Job job, Model model) {
        jobsDao.save(job);
        model.addAttribute("id", job.getId());

        return "job";
    }

    @GetMapping("/jobs/{id}")
    public String individualJob(@PathVariable Integer id, Model model) {
        Job job = jobsDao.getById(id);
        model.addAttribute("status", job.getStatus());

        return "status";
    }
}
