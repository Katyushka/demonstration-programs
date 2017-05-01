package com.demo.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author Ekaterina Pyataeva on 24.04.2017.
 */

@MappedSuperclass
public class BasicEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Transient
    public Long getId() {
        return id;
    }

    @Transient
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEntity that = (BasicEntity) o;
        return Objects.equals(getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }


}


