package com.app.proto.web.rest;

import com.app.proto.PrototypeApp;

import com.app.proto.domain.BusinessRole;
import com.app.proto.repository.BusinessRoleRepository;
import com.app.proto.service.BusinessRoleService;
import com.app.proto.repository.search.BusinessRoleSearchRepository;
import com.app.proto.service.dto.BusinessRoleDTO;
import com.app.proto.service.mapper.BusinessRoleMapper;
import com.app.proto.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the BusinessRoleResource REST controller.
 *
 * @see BusinessRoleResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = PrototypeApp.class)
public class BusinessRoleResourceIntTest {

    @Autowired
    private BusinessRoleRepository businessRoleRepository;

    @Autowired
    private BusinessRoleMapper businessRoleMapper;

    @Autowired
    private BusinessRoleService businessRoleService;

    @Autowired
    private BusinessRoleSearchRepository businessRoleSearchRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restBusinessRoleMockMvc;

    private BusinessRole businessRole;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        BusinessRoleResource businessRoleResource = new BusinessRoleResource(businessRoleService);
        this.restBusinessRoleMockMvc = MockMvcBuilders.standaloneSetup(businessRoleResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static BusinessRole createEntity(EntityManager em) {
        BusinessRole businessRole = new BusinessRole();
        return businessRole;
    }

    @Before
    public void initTest() {
        businessRoleSearchRepository.deleteAll();
        businessRole = createEntity(em);
    }

    @Test
    @Transactional
    public void createBusinessRole() throws Exception {
        int databaseSizeBeforeCreate = businessRoleRepository.findAll().size();

        // Create the BusinessRole
        BusinessRoleDTO businessRoleDTO = businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole);

        restBusinessRoleMockMvc.perform(post("/api/business-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessRole in the database
        List<BusinessRole> businessRoleList = businessRoleRepository.findAll();
        assertThat(businessRoleList).hasSize(databaseSizeBeforeCreate + 1);
        BusinessRole testBusinessRole = businessRoleList.get(businessRoleList.size() - 1);

        // Validate the BusinessRole in Elasticsearch
        BusinessRole businessRoleEs = businessRoleSearchRepository.findOne(testBusinessRole.getId());
        assertThat(businessRoleEs).isEqualToComparingFieldByField(testBusinessRole);
    }

    @Test
    @Transactional
    public void createBusinessRoleWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = businessRoleRepository.findAll().size();

        // Create the BusinessRole with an existing ID
        BusinessRole existingBusinessRole = new BusinessRole();
        existingBusinessRole.setId(1L);
        BusinessRoleDTO existingBusinessRoleDTO = businessRoleMapper.businessRoleToBusinessRoleDTO(existingBusinessRole);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBusinessRoleMockMvc.perform(post("/api/business-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(existingBusinessRoleDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Alice in the database
        List<BusinessRole> businessRoleList = businessRoleRepository.findAll();
        assertThat(businessRoleList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllBusinessRoles() throws Exception {
        // Initialize the database
        businessRoleRepository.saveAndFlush(businessRole);

        // Get all the businessRoleList
        restBusinessRoleMockMvc.perform(get("/api/business-roles?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessRole.getId().intValue())));
    }

    @Test
    @Transactional
    public void getBusinessRole() throws Exception {
        // Initialize the database
        businessRoleRepository.saveAndFlush(businessRole);

        // Get the businessRole
        restBusinessRoleMockMvc.perform(get("/api/business-roles/{id}", businessRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(businessRole.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingBusinessRole() throws Exception {
        // Get the businessRole
        restBusinessRoleMockMvc.perform(get("/api/business-roles/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBusinessRole() throws Exception {
        // Initialize the database
        businessRoleRepository.saveAndFlush(businessRole);
        businessRoleSearchRepository.save(businessRole);
        int databaseSizeBeforeUpdate = businessRoleRepository.findAll().size();

        // Update the businessRole
        BusinessRole updatedBusinessRole = businessRoleRepository.findOne(businessRole.getId());
        BusinessRoleDTO businessRoleDTO = businessRoleMapper.businessRoleToBusinessRoleDTO(updatedBusinessRole);

        restBusinessRoleMockMvc.perform(put("/api/business-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessRoleDTO)))
            .andExpect(status().isOk());

        // Validate the BusinessRole in the database
        List<BusinessRole> businessRoleList = businessRoleRepository.findAll();
        assertThat(businessRoleList).hasSize(databaseSizeBeforeUpdate);
        BusinessRole testBusinessRole = businessRoleList.get(businessRoleList.size() - 1);

        // Validate the BusinessRole in Elasticsearch
        BusinessRole businessRoleEs = businessRoleSearchRepository.findOne(testBusinessRole.getId());
        assertThat(businessRoleEs).isEqualToComparingFieldByField(testBusinessRole);
    }

    @Test
    @Transactional
    public void updateNonExistingBusinessRole() throws Exception {
        int databaseSizeBeforeUpdate = businessRoleRepository.findAll().size();

        // Create the BusinessRole
        BusinessRoleDTO businessRoleDTO = businessRoleMapper.businessRoleToBusinessRoleDTO(businessRole);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restBusinessRoleMockMvc.perform(put("/api/business-roles")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(businessRoleDTO)))
            .andExpect(status().isCreated());

        // Validate the BusinessRole in the database
        List<BusinessRole> businessRoleList = businessRoleRepository.findAll();
        assertThat(businessRoleList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteBusinessRole() throws Exception {
        // Initialize the database
        businessRoleRepository.saveAndFlush(businessRole);
        businessRoleSearchRepository.save(businessRole);
        int databaseSizeBeforeDelete = businessRoleRepository.findAll().size();

        // Get the businessRole
        restBusinessRoleMockMvc.perform(delete("/api/business-roles/{id}", businessRole.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate Elasticsearch is empty
        boolean businessRoleExistsInEs = businessRoleSearchRepository.exists(businessRole.getId());
        assertThat(businessRoleExistsInEs).isFalse();

        // Validate the database is empty
        List<BusinessRole> businessRoleList = businessRoleRepository.findAll();
        assertThat(businessRoleList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void searchBusinessRole() throws Exception {
        // Initialize the database
        businessRoleRepository.saveAndFlush(businessRole);
        businessRoleSearchRepository.save(businessRole);

        // Search the businessRole
        restBusinessRoleMockMvc.perform(get("/api/_search/business-roles?query=id:" + businessRole.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(businessRole.getId().intValue())));
    }

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BusinessRole.class);
    }
}
