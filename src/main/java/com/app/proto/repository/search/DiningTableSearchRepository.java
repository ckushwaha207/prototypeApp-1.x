package com.app.proto.repository.search;

import com.app.proto.domain.DiningTable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the DiningTable entity.
 */
public interface DiningTableSearchRepository extends ElasticsearchRepository<DiningTable, Long> {
}
