package com.website.db.repo;


import com.website.db.models.DuesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface DuesRepository extends CrudRepository<DuesEntity, Long> {
    @Query("SELECT d FROM DuesEntity d WHERE d.taxpayerByIdTaxpayer.id = :idTaxpayer")
    Collection<DuesEntity> findByIdTaxpayer(@Param("idTaxpayer") Long id);

    @Query("SELECT d.institutionsByIdInstitutions.id FROM DuesEntity d WHERE d.taxpayerByIdTaxpayer.id = :idTaxpayer")
    Long findByIdInstitutions(@Param("idTaxpayer") Long id);
}
