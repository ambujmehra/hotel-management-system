package com.marriott.hms.model.base;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * The type Base model for all entitties having id, created_at and updated_at
 *
 * @author ambujmehra
 */
@MappedSuperclass
@Data
public abstract class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "created_at", nullable = false)
    private Date createdAt;

    @Column(name = "updated_at", nullable = false)
    private Date updatedAt;

    /**
     * created_at field when entity is created
     */
    @PrePersist
    protected void onCreate() {
        updatedAt = createdAt = new Date(System.currentTimeMillis());
    }

    /**
     * updated_at field when a entity is updated
     */
    @PreUpdate
    protected void onUpdate() {
        updatedAt = new Date(System.currentTimeMillis());
    }
}
