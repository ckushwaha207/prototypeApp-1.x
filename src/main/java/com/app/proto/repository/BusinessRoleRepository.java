package com.app.proto.repository;

import com.app.proto.domain.BusinessRole;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the BusinessRole entity.
 */
@SuppressWarnings("unused")
public interface BusinessRoleRepository extends JpaRepository<BusinessRole,Long> {

}
