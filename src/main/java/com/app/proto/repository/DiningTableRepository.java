package com.app.proto.repository;

import com.app.proto.domain.DiningTable;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the DiningTable entity.
 */
@SuppressWarnings("unused")
public interface DiningTableRepository extends JpaRepository<DiningTable,Long> {

}
