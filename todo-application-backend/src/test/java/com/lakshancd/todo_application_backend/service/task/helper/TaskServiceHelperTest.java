package com.lakshancd.todo_application_backend.service.task.helper;

import com.lakshancd.todo_application_backend.common.common_utils.CommonUtils;
import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import com.lakshancd.todo_application_backend.exception.BaseRuntimeException;
import com.lakshancd.todo_application_backend.payload.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.payload.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.repository.StatusDetailsRepository;
import com.lakshancd.todo_application_backend.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("TaskServiceHelper Unit Tests")
class TaskServiceHelperTest {

    @Mock
    private CommonUtils commonUtils;

    @Mock
    private StatusDetailsRepository statusDetailsRepository;

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceHelper taskServiceHelper;

    private CreateUpdateTaskDTO sampleCreateTaskDTO;
    private TaskEntity sampleTaskEntity;
    private TaskResponseDTO sampleTaskResponseDTO;
    private StatusDetailsEntity sampleStatusEntity;

    @BeforeEach
    void setUp() {
        // Setup sample CreateUpdateTaskDTO
        sampleCreateTaskDTO = new CreateUpdateTaskDTO();
        sampleCreateTaskDTO.setTitle("Test Task");
        sampleCreateTaskDTO.setDescription("Test Description");

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
    }

    @Test
    @DisplayName("Should create task entity from DTO successfully")
    void createTaskEntityFromDTO_Success() {

        TaskEntity expectedEntity = new TaskEntity();
        expectedEntity.setTitle("Test Task");
        expectedEntity.setDescription("Test Description");
        expectedEntity.setStatusId(sampleStatusEntity);
        expectedEntity.setIsActive(true);

        when(commonUtils.convert(sampleCreateTaskDTO, TaskEntity.class)).thenReturn(expectedEntity);
        when(statusDetailsRepository.findById(10)).thenReturn(Optional.of(sampleStatusEntity));

        TaskEntity result = taskServiceHelper.createTaskEntityFromDTO(sampleCreateTaskDTO);

        assertNotNull(result);
        assertEquals("Test Task", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals(10, result.getStatusId().getStatusId());
        assertEquals("Pending", result.getStatusId().getDescription());
        assertTrue(result.getIsActive());

        verify(commonUtils).convert(sampleCreateTaskDTO, TaskEntity.class);
        verify(statusDetailsRepository).findById(10);
    }

    @Test
    @DisplayName("Should throw exception when status not found")
    void createTaskEntityFromDTO_StatusNotFound_ThrowsException() {

        TaskEntity expectedEntity = new TaskEntity();
        expectedEntity.setTitle("Test Task");
        expectedEntity.setDescription("Test Description");

        when(commonUtils.convert(sampleCreateTaskDTO, TaskEntity.class)).thenReturn(expectedEntity);
        when(statusDetailsRepository.findById(10)).thenReturn(Optional.empty());

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class, 
            () -> taskServiceHelper.createTaskEntityFromDTO(sampleCreateTaskDTO));

        assertEquals(ExceptionCode.CREATE_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(commonUtils).convert(sampleCreateTaskDTO, TaskEntity.class);
        verify(statusDetailsRepository).findById(10);
    }

    @Test
    @DisplayName("Should get status details entity successfully")
    void getStatusDetailsEntity_Success() {

        int statusId = 10;
        when(statusDetailsRepository.findById(statusId)).thenReturn(Optional.of(sampleStatusEntity));

        StatusDetailsEntity result = taskServiceHelper.getStatusDetailsEntity(statusId);

        assertNotNull(result);
        assertEquals(statusId, result.getStatusId());
        assertEquals("Pending", result.getDescription());

        verify(statusDetailsRepository).findById(statusId);
    }

    @Test
    @DisplayName("Should throw exception when status details not found")
    void getStatusDetailsEntity_NotFound_ThrowsException() {

        int statusId = 99;
        when(statusDetailsRepository.findById(statusId)).thenReturn(Optional.empty());

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class, 
            () -> taskServiceHelper.getStatusDetailsEntity(statusId));

        assertEquals(ExceptionCode.CREATE_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(statusDetailsRepository).findById(statusId);
    }

    @Test
    @DisplayName("Should convert task entity to response DTO successfully")
    void convertToTaskResponseDTO_Success() {

        TaskResponseDTO expectedResponse = new TaskResponseDTO();
        expectedResponse.setUid(1);
        expectedResponse.setTitle("Test Task");
        expectedResponse.setDescription("Test Description");
        expectedResponse.setIsActive(true);

        when(commonUtils.convert(sampleTaskEntity, TaskResponseDTO.class)).thenReturn(expectedResponse);

        TaskResponseDTO result = taskServiceHelper.convertToTaskResponseDTO(sampleTaskEntity);

        assertNotNull(result);
        assertEquals(1, result.getUid());
        assertEquals("Test Task", result.getTitle());
        assertEquals("Test Description", result.getDescription());
        assertEquals(10, result.getStatusId());
        assertEquals("Pending", result.getStatusDescription());

        verify(commonUtils).convert(sampleTaskEntity, TaskResponseDTO.class);
    }

    @Test
    @DisplayName("Should get task entity by ID successfully")
    void getTaskEntity_Success() {

        Integer taskId = 1;
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(sampleTaskEntity));

        TaskEntity result = taskServiceHelper.getTaskEntity(taskId);

        assertNotNull(result);
        assertEquals(taskId, result.getUid());
        assertEquals("Test Task", result.getTitle());
        assertEquals("Test Description", result.getDescription());

        verify(taskRepository).findById(taskId);
    }

    @Test
    @DisplayName("Should throw exception when task not found")
    void getTaskEntity_NotFound_ThrowsException() {

        Integer taskId = 999;
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        BaseRuntimeException exception = assertThrows(BaseRuntimeException.class, 
            () -> taskServiceHelper.getTaskEntity(taskId));

        assertEquals(ExceptionCode.FILED_VALIDATION, exception.getErrorDetails().getExceptionCode());
        verify(taskRepository).findById(taskId);
    }

    @Test
    @DisplayName("Should handle null status ID gracefully")
    void convertToTaskResponseDTO_NullStatusId_HandlesGracefully() {

        TaskEntity taskWithNullStatus = new TaskEntity();
        taskWithNullStatus.setUid(1);
        taskWithNullStatus.setTitle("Test Task");
        taskWithNullStatus.setDescription("Test Description");
        taskWithNullStatus.setStatusId(null);
        taskWithNullStatus.setIsActive(true);

        TaskResponseDTO expectedResponse = new TaskResponseDTO();
        expectedResponse.setUid(1);
        expectedResponse.setTitle("Test Task");
        expectedResponse.setDescription("Test Description");
        expectedResponse.setIsActive(true);

        when(commonUtils.convert(taskWithNullStatus, TaskResponseDTO.class)).thenReturn(expectedResponse);

        assertThrows(NullPointerException.class, 
            () -> taskServiceHelper.convertToTaskResponseDTO(taskWithNullStatus));

        verify(commonUtils).convert(taskWithNullStatus, TaskResponseDTO.class);
    }

    @Test
    @DisplayName("Should handle different status IDs correctly")
    void getStatusDetailsEntity_DifferentStatusIds_Success() {

        StatusDetailsEntity completedStatus = new StatusDetailsEntity();
        completedStatus.setStatusId(20);
        completedStatus.setDescription("Completed");

        when(statusDetailsRepository.findById(20)).thenReturn(Optional.of(completedStatus));

        StatusDetailsEntity result = taskServiceHelper.getStatusDetailsEntity(20);

        assertNotNull(result);
        assertEquals(20, result.getStatusId());
        assertEquals("Completed", result.getDescription());

        verify(statusDetailsRepository).findById(20);
    }
}
