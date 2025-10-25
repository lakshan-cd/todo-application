package com.lakshancd.todo_application_backend.entity.common.status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "statusdetails")
public class StatusDetailsEntity {

    @Column(length = 255)
    private String description;

    @Id
    @Basic(optional = false)
    @Column(name = "status")
    private Integer statusId;

    public StatusDetailsEntity(Integer statusId) {
        this.statusId = statusId;
    }
}
