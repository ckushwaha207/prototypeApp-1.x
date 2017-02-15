package com.app.proto.repository.search;

import com.app.proto.domain.MenuCategory;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Spring Data Elasticsearch repository for the MenuCategory entity.
 */
public interface MenuCategorySearchRepository extends ElasticsearchRepository<MenuCategory, Long> {
}
