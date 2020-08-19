package com.website.db.repo;


import com.website.db.models.DuesEntity;
import org.springframework.data.repository.CrudRepository;

public interface DuesRepository extends CrudRepository<DuesEntity, Long> {
}
