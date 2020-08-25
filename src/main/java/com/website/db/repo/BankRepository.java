package com.website.db.repo;

import com.website.db.models.BankEntity;
import com.website.db.models.JobEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface BankRepository extends CrudRepository<BankEntity, Long> {
    @Query("SELECT b FROM BankEntity b WHERE b.institutionsByIdInstitutions.id = :idInstitution")
    Collection<BankEntity> findByIdBank(@Param("idInstitution") Long id);
}
