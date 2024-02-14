package com.sbip.ch05.repo;

import com.sbip.ch05.model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : Ezekiel Eromosei
 * @code @created : 12 Feb, 2024
 */

@Repository
public interface ApplicationUserRepository extends CrudRepository<ApplicationUser, Long> {

    Optional<ApplicationUser> findApplicationUserByUsername(String username);
}
