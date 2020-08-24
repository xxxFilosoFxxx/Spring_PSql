package com.website.db.repo;

import com.website.db.models.BankEntity;
import com.website.db.models.InstitutionsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface InstitusionsRepository extends CrudRepository<InstitutionsEntity, Long> {
    @Query("SELECT i FROM InstitutionsEntity  i, DuesEntity d " +
           "WHERE i.id = :idInstitution AND d.idInstitutions = :idInstitution")
    Collection<InstitutionsEntity> findByIdDuesAndInstitutions(@Param("idInstitution") Long id);
}
