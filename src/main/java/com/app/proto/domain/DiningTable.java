package com.app.proto.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DiningTable.
 */
@Entity
@Table(name = "dining_table")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "diningtable")
public class DiningTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotNull
    @Column(name = "table_code", nullable = false)
    private String tableCode;

    @ManyToOne
    private Store store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public DiningTable number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getTableCode() {
        return tableCode;
    }

    public DiningTable tableCode(String tableCode) {
        this.tableCode = tableCode;
        return this;
    }

    public void setTableCode(String tableCode) {
        this.tableCode = tableCode;
    }

    public Store getStore() {
        return store;
    }

    public DiningTable store(Store store) {
        this.store = store;
        return this;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiningTable diningTable = (DiningTable) o;
        if (diningTable.id == null || id == null) {
            return false;
        }
        return Objects.equals(id, diningTable.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "DiningTable{" +
            "id=" + id +
            ", number='" + number + "'" +
            ", tableCode='" + tableCode + "'" +
            '}';
    }
}
