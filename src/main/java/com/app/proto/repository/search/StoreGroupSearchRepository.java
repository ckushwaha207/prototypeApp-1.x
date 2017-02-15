package com.app.proto.repository.search;

import com.app.proto.domain.StoreGroup;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the StoreGroup entity.
 */
public interface StoreGroupSearchRepository extends ElasticsearchRepository<StoreGroup, Long> {
}
