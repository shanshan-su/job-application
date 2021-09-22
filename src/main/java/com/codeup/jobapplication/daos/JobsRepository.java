package com.codeup.jobapplication.daos;

import com.codeup.jobapplication.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Job, Integer> {

}
