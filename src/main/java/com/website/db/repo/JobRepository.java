package com.website.db.repo;

import com.website.db.models.JobEntity;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobEntity, Long> {}
