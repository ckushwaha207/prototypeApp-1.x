package com.app.proto.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.app.proto.service.BusinessRoleService;
import com.app.proto.web.rest.util.HeaderUtil;
import com.app.proto.web.rest.util.PaginationUtil;
import com.app.proto.service.dto.BusinessRoleDTO;
import io.swagger.annotations.ApiParam;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing BusinessRole.
 */
@RestController
@RequestMapping("/api")
public class BusinessRoleResource {

    private final Logger log = LoggerFactory.getLogger(BusinessRoleResource.class);

    private static final String ENTITY_NAME = "businessRole";
        
    private final BusinessRoleService businessRoleService;

    public BusinessRoleResource(BusinessRoleService businessRoleService) {
        this.businessRoleService = businessRoleService;
    }

    /**
     * POST  /business-roles : Create a new businessRole.
     *
     * @param businessRoleDTO the businessRoleDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new businessRoleDTO, or with status 400 (Bad Request) if the businessRole has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/business-roles")
    @Timed
    public ResponseEntity<BusinessRoleDTO> createBusinessRole(@RequestBody BusinessRoleDTO businessRoleDTO) throws URISyntaxException {
        log.debug("REST request to save BusinessRole : {}", businessRoleDTO);
        if (businessRoleDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new businessRole cannot already have an ID")).body(null);
        }
        BusinessRoleDTO result = businessRoleService.save(businessRoleDTO);
        return ResponseEntity.created(new URI("/api/business-roles/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /business-roles : Updates an existing businessRole.
     *
     * @param businessRoleDTO the businessRoleDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated businessRoleDTO,
     * or with status 400 (Bad Request) if the businessRoleDTO is not valid,
     * or with status 500 (Internal Server Error) if the businessRoleDTO couldnt be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/business-roles")
    @Timed
    public ResponseEntity<BusinessRoleDTO> updateBusinessRole(@RequestBody BusinessRoleDTO businessRoleDTO) throws URISyntaxException {
        log.debug("REST request to update BusinessRole : {}", businessRoleDTO);
        if (businessRoleDTO.getId() == null) {
            return createBusinessRole(businessRoleDTO);
        }
        BusinessRoleDTO result = businessRoleService.save(businessRoleDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, businessRoleDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /business-roles : get all the businessRoles.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of businessRoles in body
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/business-roles")
    @Timed
    public ResponseEntity<List<BusinessRoleDTO>> getAllBusinessRoles(@ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to get a page of BusinessRoles");
        Page<BusinessRoleDTO> page = businessRoleService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/business-roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    /**
     * GET  /business-roles/:id : get the "id" businessRole.
     *
     * @param id the id of the businessRoleDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the businessRoleDTO, or with status 404 (Not Found)
     */
    @GetMapping("/business-roles/{id}")
    @Timed
    public ResponseEntity<BusinessRoleDTO> getBusinessRole(@PathVariable Long id) {
        log.debug("REST request to get BusinessRole : {}", id);
        BusinessRoleDTO businessRoleDTO = businessRoleService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(businessRoleDTO));
    }

    /**
     * DELETE  /business-roles/:id : delete the "id" businessRole.
     *
     * @param id the id of the businessRoleDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/business-roles/{id}")
    @Timed
    public ResponseEntity<Void> deleteBusinessRole(@PathVariable Long id) {
        log.debug("REST request to delete BusinessRole : {}", id);
        businessRoleService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }

    /**
     * SEARCH  /_search/business-roles?query=:query : search for the businessRole corresponding
     * to the query.
     *
     * @param query the query of the businessRole search 
     * @param pageable the pagination information
     * @return the result of the search
     * @throws URISyntaxException if there is an error to generate the pagination HTTP headers
     */
    @GetMapping("/_search/business-roles")
    @Timed
    public ResponseEntity<List<BusinessRoleDTO>> searchBusinessRoles(@RequestParam String query, @ApiParam Pageable pageable)
        throws URISyntaxException {
        log.debug("REST request to search for a page of BusinessRoles for query {}", query);
        Page<BusinessRoleDTO> page = businessRoleService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query, page, "/api/_search/business-roles");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }


}
