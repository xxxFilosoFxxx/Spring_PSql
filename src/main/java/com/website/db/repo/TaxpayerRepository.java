package com.website.db.repo;

import com.website.db.models.TaxpayerEntity;
import org.springframework.data.repository.CrudRepository;

public interface TaxpayerRepository extends CrudRepository<TaxpayerEntity, Long> {

}
