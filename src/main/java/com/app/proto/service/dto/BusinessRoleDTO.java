package com.app.proto.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BusinessRole entity.
 */
public class BusinessRoleDTO implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BusinessRoleDTO businessRoleDTO = (BusinessRoleDTO) o;

        if ( ! Objects.equals(id, businessRoleDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "BusinessRoleDTO{" +
            "id=" + id +
            '}';
    }
}
