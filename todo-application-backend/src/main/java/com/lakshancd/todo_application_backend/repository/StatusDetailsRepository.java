package com.lakshancd.todo_application_backend.repository;

import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDetailsRepository extends JpaRepository<StatusDetailsEntity, Integer>, JpaSpecificationExecutor<StatusDetailsEntity> {
}
