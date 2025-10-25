package com.lakshancd.todo_application_backend.repository;

import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity,Integer>, JpaSpecificationExecutor<TaskEntity> {
}
