package com.website.db.repo;

import com.website.db.models.JobEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface JobRepository extends CrudRepository<JobEntity, Long> {
    @Query("SELECT j FROM JobEntity j WHERE j.taxpayerByIdTaxpayer.id = :idTaxpayer")
    Collection<JobEntity> findByIdTaxpayer(@Param("idTaxpayer") Long id);
}
