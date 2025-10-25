package com.lakshancd.todo_application_backend.service.task.helper.filter;

import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;

public class TaskSpecification implements Serializable {
    public static Specification<TaskEntity> buildSpecification(String title, Integer statusId, Boolean isActive) {
        return new CustomTaskSpecification(title, statusId, isActive);
    }
}
