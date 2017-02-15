package com.app.proto.service.impl;

import com.app.proto.service.BusinessRoleService;
import com.app.proto.domain.BusinessRole;
import com.app.proto.repository.BusinessRoleRepository;
import com.app.proto.repository.search.BusinessRoleSearchRepository;
import com.app.proto.service.dto.BusinessRoleDTO;
import com.app.proto.service.mapper.BusinessRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing BusinessRole.
 */
@Service
@Transactional
public class BusinessRoleServiceImpl implements BusinessRoleService{

    private final Logger log = LoggerFactory.getLogger(BusinessRoleServiceImpl.class);
    
    private final BusinessRoleRepository businessRoleRepository;

    private final BusinessRoleMapper businessRoleMapper;

    private final BusinessRoleSearchRepository businessRoleSearchRepository;

    public BusinessRoleServiceImpl(BusinessRoleRepository businessRoleRepository, BusinessRoleMapper businessRoleMapper, BusinessRoleSearchRepository businessRoleSearchRepository) {
        this.businessRoleRepository = businessRoleRepository;
        this.businessRoleMapper = businessRoleMapper;
        this.businessRoleSearchRepository = businessRoleSearchRepository;
    }

    /**
     * Save a businessRole.
     *
     * @param businessRoleDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public BusinessRoleDTO save(BusinessRoleDTO businessRoleDTO) {
        log.debug("Request to save BusinessRole : {}", businessRoleDTO);
        BusinessRole businessRole = businessRoleMapper.businessRoleDTOToBusinessRole(businessRoleDTO);
        businessRole = businessRoleRepository.save(businessRole);
        BusinessRoleDTO result = businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole);
        businessRoleSearchRepository.save(businessRole);
        return result;
    }

    /**
     *  Get all the businessRoles.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BusinessRoleDTO> findAll(Pageable pageable) {
        log.debug("Request to get all BusinessRoles");
        Page<BusinessRole> result = businessRoleRepository.findAll(pageable);
        return result.map(businessRole -> businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole));
    }

    /**
     *  Get one businessRole by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public BusinessRoleDTO findOne(Long id) {
        log.debug("Request to get BusinessRole : {}", id);
        BusinessRole businessRole = businessRoleRepository.findOne(id);
        BusinessRoleDTO businessRoleDTO = businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole);
        return businessRoleDTO;
    }

    /**
     *  Delete the  businessRole by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete BusinessRole : {}", id);
        businessRoleRepository.delete(id);
        businessRoleSearchRepository.delete(id);
    }

    /**
     * Search for the businessRole corresponding to the query.
     *
     *  @param query the query of the search
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<BusinessRoleDTO> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of BusinessRoles for query {}", query);
        Page<BusinessRole> result = businessRoleSearchRepository.search(queryStringQuery(query), pageable);
        return result.map(businessRole -> businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole));
    }
}
