package com.lakshancd.todo_application_backend.payload.task;

import lombok.Data;

import java.util.Date;

@Data
public class TaskResponseDTO {
    private Integer uid;
    private String title;
    private String description;
    private Integer statusId;
    private String statusDescription;
    private Integer isActive;
    private Date createdDate;
    private String createdBy;
    private Date modifiedDate;
    private String modifiedBy;
}
