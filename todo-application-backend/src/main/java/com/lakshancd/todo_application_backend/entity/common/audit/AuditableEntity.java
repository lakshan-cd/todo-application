package com.lakshancd.todo_application_backend.entity.common.audit;

import com.lakshancd.todo_application_backend.entity.common.base.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@MappedSuperclass //just use with another entity
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)//capture auditing events
@Audited
public class AuditableEntity extends BaseEntity {

    @Temporal(TemporalType.TIMESTAMP)//get current system time
    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

    @CreatedBy
    @Column(name = "created_by")
    private String createdBy;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "modified_date")
    private Date modifiedDate;

    @LastModifiedBy
    @Column(name = "modified_by")
    private String modifiedBy;

    public AuditableEntity(Integer uid) {
        super(uid);
    }
}
