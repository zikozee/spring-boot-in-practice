package com.sbip.ch06.repo;

import com.sbip.ch06.model.TotpDetails;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 22 Feb, 2024
 */


public interface TotpRepository extends CrudRepository<TotpDetails, Long> {
    void deleteByUsername(String username);
    Optional<TotpDetails> findByUsername(String username);
}
