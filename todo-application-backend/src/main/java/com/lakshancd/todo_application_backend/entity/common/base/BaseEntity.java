package com.lakshancd.todo_application_backend.entity.common.base;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@NoArgsConstructor
@Getter
@Setter
public class BaseEntity {
    /** The property id is the generated id for all entity */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid", nullable = false, updatable = false)
    private Integer uid;

    public BaseEntity(Integer uid) {
        this.uid = uid;
    }
}
