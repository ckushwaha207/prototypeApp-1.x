package com.app.proto.repository.search;

import com.app.proto.domain.BusinessRole;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the BusinessRole entity.
 */
public interface BusinessRoleSearchRepository extends ElasticsearchRepository<BusinessRole, Long> {
}
