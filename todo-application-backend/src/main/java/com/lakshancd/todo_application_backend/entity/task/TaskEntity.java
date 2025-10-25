package com.lakshancd.todo_application_backend.entity.task;

import com.lakshancd.todo_application_backend.entity.common.audit.AuditableEntity;
import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "task")
public class TaskEntity extends AuditableEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status")
    private StatusDetailsEntity statusId;

    @Column(name = "is_active")
    private Boolean isActive;
}
