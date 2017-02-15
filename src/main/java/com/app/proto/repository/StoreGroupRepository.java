package com.app.proto.repository;

import com.app.proto.domain.StoreGroup;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the StoreGroup entity.
 */
@SuppressWarnings("unused")
public interface StoreGroupRepository extends JpaRepository<StoreGroup,Long> {

}
