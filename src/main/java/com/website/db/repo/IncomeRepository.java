package com.website.db.repo;

import com.website.db.models.IncomeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface IncomeRepository extends CrudRepository<IncomeEntity, Long> {
    @Query("SELECT i FROM IncomeEntity i WHERE i.taxpayerByIdTaxpayer.id = :idTaxpayer")
    Collection<IncomeEntity> findByIdTaxpayer(@Param("idTaxpayer") Long id);
}
