package com.lakshancd.todo_application_backend.service.task.impl;

import com.lakshancd.todo_application_backend.common.common_utils.PutEntityUpdater;
import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import com.lakshancd.todo_application_backend.common.util.SortUtils;
import com.lakshancd.todo_application_backend.dto.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.dto.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import com.lakshancd.todo_application_backend.exception.BaseRuntimeException;
import com.lakshancd.todo_application_backend.payload.response.error.ErrorDetails;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginatedResponse;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginationInfo;
import com.lakshancd.todo_application_backend.repository.TaskRepository;
import com.lakshancd.todo_application_backend.service.task.TaskService;
import com.lakshancd.todo_application_backend.service.task.helper.TaskServiceHelper;
import com.lakshancd.todo_application_backend.service.task.helper.filter.TaskSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskServiceHelper taskServiceHelper;
    private final TaskRepository taskRepository;

    @Override
    public TaskResponseDTO createTask(CreateUpdateTaskDTO request) {
        TaskEntity taskEntity = taskServiceHelper.createTaskEntityFromDTO(request);
        TaskEntity savedTask = taskRepository.save(taskEntity);
        return taskServiceHelper.convertToTaskResponseDTO(savedTask);
    }

    @Override
    public PaginatedResponse<TaskResponseDTO> getAllTasks(Integer page, Integer pageSize, String title, Integer statusId, Boolean isActive, String sortDirection) {
        Sort sort = SortUtils.getSort(sortDirection, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Specification<TaskEntity> specification = TaskSpecification.buildSpecification(title,statusId , isActive);

        Page<TaskEntity> entityPage = taskRepository.findAll(specification, pageable);
        List<TaskResponseDTO> list = entityPage.getContent().stream()
                .map(taskServiceHelper::convertToTaskResponseDTO)
                .toList();

        return PaginatedResponse.<TaskResponseDTO>builder()
                .result(list)
                .paginationInfo(new PaginationInfo(page, entityPage.getTotalPages(), entityPage.getTotalElements(), pageSize))
                .build();
    }

    @Override
    public TaskResponseDTO getTaskById(Integer id) {
        TaskEntity taskEntity = taskServiceHelper.getTaskEntity(id);
        return taskServiceHelper.convertToTaskResponseDTO(taskEntity);
    }

    @Override
    public TaskResponseDTO completeTask(Integer id, Map<String, Object> updates) {
        TaskEntity existingEntity = taskServiceHelper.getTaskEntity(id);
        if (updates.containsKey("statusId")) {
            int statusId = Integer.parseInt(updates.get("statusId").toString());
            if (existingEntity.getStatusId().getStatusId().equals(20)) {
                throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.PATCH_VALIDATION, ResponseConstants.getAlreadyCompleted(ResponseConstants.TASK)));
            }
            if (Objects.equals(statusId, 20)) {
                StatusDetailsEntity statusDetailsEntity = taskServiceHelper.getStatusDetailsEntity(statusId);
                existingEntity.setStatusId(statusDetailsEntity);
                TaskEntity saved = taskRepository.save(existingEntity);
                return taskServiceHelper.convertToTaskResponseDTO(saved);
            } else {
                throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.PATCH_VALIDATION, ResponseConstants.getInvalidFieldMessage(ResponseConstants.TASK, "Status")));
            }
        }
        throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.PATCH_VALIDATION, ResponseConstants.getNotUpdatedMessage(ResponseConstants.TASK)));
    }

    @Override
    public TaskResponseDTO deleteTask(Integer id, Map<String, Object> updates) {
        TaskEntity existingEntity = taskServiceHelper.getTaskEntity(id);
        if (updates.containsKey("isActive")){
            boolean isActive = Boolean.parseBoolean(updates.get("isActive").toString());
            if (!isActive){
                existingEntity.setIsActive(false);
                TaskEntity saved = taskRepository.save(existingEntity);
                return taskServiceHelper.convertToTaskResponseDTO(saved);
            } else {
                throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.PATCH_VALIDATION, ResponseConstants.getInvalidFieldMessage(ResponseConstants.TASK, "isActive")));
            }
        } else {
            throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.PATCH_VALIDATION, ResponseConstants.getNotDeletedMessage(ResponseConstants.TASK)));
        }
    }

    @Override
    public TaskResponseDTO updateTask(Integer id, CreateUpdateTaskDTO request) {
        TaskEntity existingEntity = taskServiceHelper.getTaskEntity(id);

        PutEntityUpdater<TaskEntity, CreateUpdateTaskDTO> entityUpdater = new PutEntityUpdater<>();
        boolean isChanged = entityUpdater.updateEntity(existingEntity, request);

        if (isChanged){
            TaskEntity saved = taskRepository.save(existingEntity);
            return taskServiceHelper.convertToTaskResponseDTO(saved);
        }
        throw new BaseRuntimeException(new ErrorDetails(ExceptionCode.UPDATE_VALIDATION, ResponseConstants.getNotUpdatedMessage(ResponseConstants.TASK)));
    }
}
