package com.app.proto.service;

import com.app.proto.service.dto.MenuDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing Menu.
 */
public interface MenuService {

    /**
     * Save a menu.
     *
     * @param menuDTO the entity to save
     * @return the persisted entity
     */
    MenuDTO save(MenuDTO menuDTO);

    /**
     *  Get all the menus.
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MenuDTO> findAll(Pageable pageable);

    /**
     *  Get all the menus by store id.
     *
     *  @param id the store id
     *  @return the list of entities
     */
    List<MenuDTO> findAllByStoreId(Long id);

    /**
     *  Get the "id" menu.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    MenuDTO findOne(Long id);

    /**
     *  Delete the "id" menu.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the menu corresponding to the query.
     *
     *  @param query the query of the search
     *
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<MenuDTO> search(String query, Pageable pageable);
}
