package com.app.proto.repository.search;

import com.app.proto.domain.TransactionStatus;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the TransactionStatus entity.
 */
public interface TransactionStatusSearchRepository extends ElasticsearchRepository<TransactionStatus, Long> {
}
