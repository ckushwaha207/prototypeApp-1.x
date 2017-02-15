package com.app.proto.repository;

import com.app.proto.domain.TransactionStatus;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the TransactionStatus entity.
 */
@SuppressWarnings("unused")
public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,Long> {

}
