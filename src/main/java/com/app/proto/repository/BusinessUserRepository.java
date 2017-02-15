package com.app.proto.repository;

import com.app.proto.domain.BusinessUser;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BusinessUser entity.
 */
@SuppressWarnings("unused")
public interface BusinessUserRepository extends JpaRepository<BusinessUser,Long> {

}
