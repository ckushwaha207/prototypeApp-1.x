package com.app.proto.repository.search;

import com.app.proto.domain.MenuItem;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MenuItem entity.
 */
public interface MenuItemSearchRepository extends ElasticsearchRepository<MenuItem, Long> {
}
