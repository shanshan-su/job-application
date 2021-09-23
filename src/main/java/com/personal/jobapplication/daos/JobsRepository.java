package com.personal.jobapplication.daos;

import com.personal.jobapplication.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobsRepository extends JpaRepository<Job, Long> {

}
