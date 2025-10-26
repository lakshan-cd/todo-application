package com.lakshancd.todo_application_backend.service.task.helper;

import com.lakshancd.todo_application_backend.common.common_utils.CommonUtils;
import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import com.lakshancd.todo_application_backend.payload.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.payload.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import com.lakshancd.todo_application_backend.exception.BaseRuntimeException;
import com.lakshancd.todo_application_backend.payload.response.error.ErrorDetails;
import com.lakshancd.todo_application_backend.repository.StatusDetailsRepository;
import com.lakshancd.todo_application_backend.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskServiceHelper {
    private final CommonUtils commonUtils;
    private final StatusDetailsRepository statusDetailsRepository;
    private final TaskRepository taskRepository;

    public TaskEntity createTaskEntityFromDTO(CreateUpdateTaskDTO request) {
        TaskEntity taskEntity = commonUtils.convert(request, TaskEntity.class);

        StatusDetailsEntity statusDetailsEntity = getStatusDetailsEntity(10);
        taskEntity.setStatusId(statusDetailsEntity);
        taskEntity.setIsActive(true);
        return taskEntity;
    }

    public StatusDetailsEntity getStatusDetailsEntity(int i) {
        return statusDetailsRepository.findById(i).orElseThrow(() ->
                new BaseRuntimeException(new ErrorDetails(ExceptionCode.CREATE_VALIDATION , ResponseConstants.getNotFoundMessage(ResponseConstants.STATUS))));
    }

    public TaskResponseDTO convertToTaskResponseDTO(TaskEntity savedTask) {
        TaskResponseDTO responseDTO = commonUtils.convert(savedTask, TaskResponseDTO.class);
        responseDTO.setStatusId(savedTask.getStatusId().getStatusId());
        responseDTO.setStatusDescription(savedTask.getStatusId().getDescription());
        return responseDTO;
    }

    public TaskEntity getTaskEntity(Integer id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new BaseRuntimeException(new ErrorDetails(ExceptionCode.FILED_VALIDATION , ResponseConstants.getNotFoundMessage(ResponseConstants.TASK))));
    }
}
