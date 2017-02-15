package com.app.proto.repository.search;

import com.app.proto.domain.BusinessUser;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the BusinessUser entity.
 */
public interface BusinessUserSearchRepository extends ElasticsearchRepository<BusinessUser, Long> {
}
