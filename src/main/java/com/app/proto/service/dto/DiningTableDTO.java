package com.app.proto.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the DiningTable entity.
 */
public class DiningTableDTO implements Serializable {

    private Long id;

    @NotNull
    private Integer number;

    @NotNull
    private String tableCode;

    private Long storeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public String getTableCode() {
        return tableCode;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DiningTableDTO diningTableDTO = (DiningTableDTO) o;

        if ( ! Objects.equals(id, diningTableDTO.id)) { return false; }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DiningTableDTO{" +
            "id=" + id +
            ", number='" + number + "'" +
            ", tableCode='" + tableCode + "'" +
            '}';
    }
}
