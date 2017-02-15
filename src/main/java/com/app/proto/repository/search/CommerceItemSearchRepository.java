package com.app.proto.repository.search;

import com.app.proto.domain.CommerceItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the CommerceItem entity.
 */
public interface CommerceItemSearchRepository extends ElasticsearchRepository<CommerceItem, Long> {
}
