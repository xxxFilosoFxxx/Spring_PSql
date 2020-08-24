package com.website.db.repo;

import com.website.db.models.BankEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface BankRepository extends CrudRepository<BankEntity, Long> {
    @Query("SELECT b FROM BankEntity b, IncomeEntity i " +
           "WHERE b.id = :idBank AND i.idBank = :idBank")
    Collection<BankEntity> findByIdIncomeAndBank(@Param("idBank") Long id);

    @Query("SELECT b FROM BankEntity b WHERE b.idInstitutions = :idInstitution")
    Collection<BankEntity> findByIdAndBank(@Param("idInstitution") Long id);
}
