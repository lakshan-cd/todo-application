package com.lakshancd.todo_application_backend.service.task.impl;

import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import com.lakshancd.todo_application_backend.exception.BaseRuntimeException;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginatedResponse;
import com.lakshancd.todo_application_backend.payload.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.payload.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.repository.TaskRepository;
import com.lakshancd.todo_application_backend.service.task.helper.TaskServiceHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskService Unit Tests")
class TaskServiceImplTest {

    @Mock
    private TaskServiceHelper taskServiceHelper;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImpl taskService;

    private TaskEntity sampleTaskEntity;
    private TaskResponseDTO sampleTaskResponseDTO;
    private CreateUpdateTaskDTO sampleCreateTaskDTO;
    private StatusDetailsEntity sampleStatusEntity;

    @BeforeEach
    void setUp() {
        // Setup sample StatusDetailsEntity
        sampleStatusEntity = new StatusDetailsEntity();
        sampleStatusEntity.setStatusId(10);
        sampleStatusEntity.setDescription("Pending");

        // Setup sample TaskEntity
        sampleTaskEntity = new TaskEntity();
        sampleTaskEntity.setUid(1);
        sampleTaskEntity.setTitle("Test Task");
        sampleTaskEntity.setDescription("Test Description");
        sampleTaskEntity.setStatusId(sampleStatusEntity);
        sampleTaskEntity.setIsActive(true);
        sampleTaskEntity.setCreatedBy("testUser");

        // Setup sample TaskResponseDTO
        sampleTaskResponseDTO = new TaskResponseDTO();
        sampleTaskResponseDTO.setUid(1);
        sampleTaskResponseDTO.setTitle("Test Task");
        sampleTaskResponseDTO.setDescription("Test Description");
        sampleTaskResponseDTO.setStatusId(10);
        sampleTaskResponseDTO.setStatusDescription("Pending");
        sampleTaskResponseDTO.setIsActive(true);

        // Setup sample CreateUpdateTaskDTO
        sampleCreateTaskDTO = new CreateUpdateTaskDTO();
        sampleCreateTaskDTO.setTitle("Test Task");
        sampleCreateTaskDTO.setDescription("Test Description");
    }

    @Test
    @DisplayName("Should create task successfully")
    void createTask_Success() {
        when(taskServiceHelper.createTaskEntityFromDTO(sampleCreateTaskDTO)).thenReturn(sampleTaskEntity);
        when(taskRepository.save(sampleTaskEntity)).thenReturn(sampleTaskEntity);
        when(taskServiceHelper.convertToTaskResponseDTO(sampleTaskEntity)).thenReturn(sampleTaskResponseDTO);

        TaskResponseDTO result = taskService.createTask(sampleCreateTaskDTO);

        assertNotNull(result);
        assertEquals(sampleTaskResponseDTO.getUid(), result.getUid());
        assertEquals(sampleTaskResponseDTO.getTitle(), result.getTitle());
        assertEquals(sampleTaskResponseDTO.getDescription(), result.getDescription());
        assertEquals(sampleTaskResponseDTO.getStatusId(), result.getStatusId());

        verify(taskServiceHelper).createTaskEntityFromDTO(sampleCreateTaskDTO);
        verify(taskRepository).save(sampleTaskEntity);
        verify(taskServiceHelper).convertToTaskResponseDTO(sampleTaskEntity);
    }

    @Test
    @DisplayName("Should get all tasks with pagination successfully")
    void getAllTasks_Success() {
        int page = 1;
        int pageSize = 10;
        String sortDirection = "desc";
        List<TaskEntity> taskEntities = Arrays.asList(sampleTaskEntity);
        Page<TaskEntity> taskPage = new PageImpl<>(taskEntities, PageRequest.of(0, pageSize), 1);

        when(taskRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(taskPage);
        when(taskServiceHelper.convertToTaskResponseDTO(any(TaskEntity.class))).thenReturn(sampleTaskResponseDTO);

        PaginatedResponse<TaskResponseDTO> result = taskService.getAllTasks(page, pageSize, null, null, null, sortDirection);

        assertNotNull(result);
        assertNotNull(result.getResult());
        assertEquals(1, result.getResult().size());
        assertEquals(page, result.getPaginationInfo().getCurrentPage());
        assertEquals(pageSize, result.getPaginationInfo().getPageSize());
        assertEquals(1, result.getPaginationInfo().getTotalResults());

        verify(taskRepository).findAll(any(Specification.class), any(Pageable.class));
        verify(taskServiceHelper).convertToTaskResponseDTO(sampleTaskEntity);
    }

    @Test
    @DisplayName("Should get task by ID successfully")
    void getTaskById_Success() {
        Integer taskId = 1;
        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);
        when(taskServiceHelper.convertToTaskResponseDTO(sampleTaskEntity)).thenReturn(sampleTaskResponseDTO);

        TaskResponseDTO result = taskService.getTaskById(taskId);

        assertNotNull(result);
        assertEquals(sampleTaskResponseDTO.getUid(), result.getUid());
        assertEquals(sampleTaskResponseDTO.getTitle(), result.getTitle());

        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskServiceHelper).convertToTaskResponseDTO(sampleTaskEntity);
    }

    @Test
    @DisplayName("Should complete task successfully")
    void completeTask_Success() {
        Integer taskId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("statusId", 20);

        StatusDetailsEntity completedStatus = new StatusDetailsEntity();
        completedStatus.setStatusId(20);
        completedStatus.setDescription("Completed");

        TaskEntity completedTask = new TaskEntity();
        completedTask.setUid(1);
        completedTask.setTitle("Test Task");
        completedTask.setStatusId(completedStatus);
        completedTask.setIsActive(true);

        TaskResponseDTO completedResponse = new TaskResponseDTO();
        completedResponse.setUid(1);
        completedResponse.setTitle("Test Task");
        completedResponse.setStatusId(20);
        completedResponse.setStatusDescription("Completed");

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);
        when(taskServiceHelper.getStatusDetailsEntity(20)).thenReturn(completedStatus);
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(completedTask);
        when(taskServiceHelper.convertToTaskResponseDTO(completedTask)).thenReturn(completedResponse);

        TaskResponseDTO result = taskService.completeTask(taskId, updates);

        assertNotNull(result);
        assertEquals(20, result.getStatusId());
        assertEquals("Completed", result.getStatusDescription());

        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskServiceHelper).getStatusDetailsEntity(20);
        verify(taskRepository).save(any(TaskEntity.class));
        verify(taskServiceHelper).convertToTaskResponseDTO(completedTask);
    }

    @Test
    @DisplayName("Should throw exception when trying to complete already completed task")
    void completeTask_AlreadyCompleted_ThrowsException() {
        Integer taskId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("statusId", 20);

        sampleTaskEntity.getStatusId().setStatusId(20); // Already completed
        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class,
            () -> taskService.completeTask(taskId, updates));

        assertEquals(ExceptionCode.PATCH_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    @DisplayName("Should throw exception when invalid status ID provided")
    void completeTask_InvalidStatusId_ThrowsException() {
        Integer taskId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("statusId", 30);

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class,
            () -> taskService.completeTask(taskId, updates));

        assertEquals(ExceptionCode.PATCH_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    @DisplayName("Should delete task successfully")
    void deleteTask_Success() {
        Integer taskId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("isActive", false);

        TaskEntity deletedTask = new TaskEntity();
        deletedTask.setUid(1);
        deletedTask.setTitle("Test Task");
        deletedTask.setIsActive(false);

        TaskResponseDTO deletedResponse = new TaskResponseDTO();
        deletedResponse.setUid(1);
        deletedResponse.setTitle("Test Task");
        deletedResponse.setIsActive(false);

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(deletedTask);
        when(taskServiceHelper.convertToTaskResponseDTO(deletedTask)).thenReturn(deletedResponse);

        TaskResponseDTO result = taskService.deleteTask(taskId, updates);

        assertNotNull(result);
        assertEquals(false, result.getIsActive());

        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository).save(any(TaskEntity.class));
        verify(taskServiceHelper).convertToTaskResponseDTO(deletedTask);
    }

    @Test
    @DisplayName("Should throw exception when invalid isActive value provided")
    void deleteTask_InvalidIsActive_ThrowsException() {
        Integer taskId = 1;
        Map<String, Object> updates = new HashMap<>();
        updates.put("isActive", true);

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class,
            () -> taskService.deleteTask(taskId, updates));

        assertEquals(ExceptionCode.PATCH_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }

    @Test
    @DisplayName("Should update task successfully")
    void updateTask_Success() {
        Integer taskId = 1;
        CreateUpdateTaskDTO updateDTO = new CreateUpdateTaskDTO();
        updateDTO.setTitle("Updated Task");
        updateDTO.setDescription("Updated Description");

        TaskEntity updatedTask = new TaskEntity();
        updatedTask.setUid(1);
        updatedTask.setTitle("Updated Task");
        updatedTask.setDescription("Updated Description");
        updatedTask.setStatusId(sampleStatusEntity);

        TaskResponseDTO updatedResponse = new TaskResponseDTO();
        updatedResponse.setUid(1);
        updatedResponse.setTitle("Updated Task");
        updatedResponse.setDescription("Updated Description");

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);
        when(taskRepository.save(any(TaskEntity.class))).thenReturn(updatedTask);
        when(taskServiceHelper.convertToTaskResponseDTO(updatedTask)).thenReturn(updatedResponse);

        TaskResponseDTO result = taskService.updateTask(taskId, updateDTO);

        assertNotNull(result);
        assertEquals("Updated Task", result.getTitle());
        assertEquals("Updated Description", result.getDescription());

        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository).save(any(TaskEntity.class));
        verify(taskServiceHelper).convertToTaskResponseDTO(updatedTask);
    }

    @Test
    @DisplayName("Should throw exception when no changes made during update")
    void updateTask_NoChanges_ThrowsException() {
        Integer taskId = 1;
        CreateUpdateTaskDTO updateDTO = new CreateUpdateTaskDTO();
        updateDTO.setTitle("Test Task");
        updateDTO.setDescription("Test Description");

        when(taskServiceHelper.getTaskEntity(taskId)).thenReturn(sampleTaskEntity);

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class,
                () -> taskService.updateTask(taskId, updateDTO));

        assertEquals(ExceptionCode.UPDATE_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(taskServiceHelper).getTaskEntity(taskId);
        verify(taskRepository, never()).save(any(TaskEntity.class));
    }
}
