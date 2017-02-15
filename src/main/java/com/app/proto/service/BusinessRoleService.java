package com.app.proto.service;

import com.app.proto.service.dto.BusinessRoleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * Service Interface for managing BusinessRole.
 */
public interface BusinessRoleService {

    /**
     * Save a businessRole.
     *
     * @param businessRoleDTO the entity to save
     * @return the persisted entity
     */
    BusinessRoleDTO save(BusinessRoleDTO businessRoleDTO);

    /**
     *  Get all the businessRoles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BusinessRoleDTO> findAll(Pageable pageable);

    /**
     *  Get the "id" businessRole.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    BusinessRoleDTO findOne(Long id);

    /**
     *  Delete the "id" businessRole.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);

    /**
     * Search for the businessRole corresponding to the query.
     *
     *  @param query the query of the search
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    Page<BusinessRoleDTO> search(String query, Pageable pageable);
}
