package com.website.db.repo;

import com.website.db.models.IncomeEntity;
import org.springframework.data.repository.CrudRepository;

public interface IncomeRepository extends CrudRepository<IncomeEntity, Long> {
}
