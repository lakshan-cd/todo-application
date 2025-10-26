package com.lakshancd.todo_application_backend.service.task.integration;

import com.lakshancd.todo_application_backend.entity.common.status.StatusDetailsEntity;
import com.lakshancd.todo_application_backend.entity.task.TaskEntity;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginatedResponse;
import com.lakshancd.todo_application_backend.payload.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.payload.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.repository.StatusDetailsRepository;
import com.lakshancd.todo_application_backend.repository.TaskRepository;
import com.lakshancd.todo_application_backend.service.task.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
@DisplayName("TaskService Integration Tests")
class TaskServiceIntegrationTest {

    @Container
    static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("test")
            .withPassword("test");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mysql::getJdbcUrl);
        registry.add("spring.datasource.username", mysql::getUsername);
        registry.add("spring.datasource.password", mysql::getPassword);
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-drop");
    }

    @Autowired
    private TaskService taskService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private StatusDetailsRepository statusDetailsRepository;

    private StatusDetailsEntity pendingStatus;
    private StatusDetailsEntity completedStatus;

    @BeforeEach
    void setUp() {
        taskRepository.deleteAll();
        statusDetailsRepository.deleteAll();

        // Create status entities
        pendingStatus = new StatusDetailsEntity();
        pendingStatus.setStatusId(10);
        pendingStatus.setDescription("Pending");
        statusDetailsRepository.save(pendingStatus);

        completedStatus = new StatusDetailsEntity();
        completedStatus.setStatusId(20);
        completedStatus.setDescription("Completed");
        statusDetailsRepository.save(completedStatus);
    }

    @Test
    @DisplayName("Should create task with database persistence")
    void createTask_Integration_Success() {
        CreateUpdateTaskDTO createDTO = new CreateUpdateTaskDTO();
        createDTO.setTitle("Integration Test Task");
        createDTO.setDescription("Integration test description");

        TaskResponseDTO result = taskService.createTask(createDTO);

        assertNotNull(result);
        assertNotNull(result.getUid());
        assertEquals("Integration Test Task", result.getTitle());
        assertEquals("Integration test description", result.getDescription());
        assertEquals(10, result.getStatusId());
        assertEquals("Pending", result.getStatusDescription());
        assertEquals(true, result.getIsActive());

        TaskEntity savedEntity = taskRepository.findById(result.getUid()).orElse(null);
        assertNotNull(savedEntity);
        assertEquals("Integration Test Task", savedEntity.getTitle());
        assertEquals("Integration test description", savedEntity.getDescription());
        assertEquals(10, savedEntity.getStatusId().getStatusId());
        assertTrue(savedEntity.getIsActive());
    }

    @Test
    @DisplayName("Should retrieve all tasks with pagination")
    void getAllTasks_Integration_Success() {
        createTestTasks(5);

        PaginatedResponse<TaskResponseDTO> result = taskService.getAllTasks(1, 3, null, null, null, "desc");

        assertNotNull(result);
        assertNotNull(result.getResult());
        assertEquals(3, result.getResult().size());
        assertEquals(1, result.getPaginationInfo().getCurrentPage());
        assertEquals(3, result.getPaginationInfo().getPageSize());
        assertEquals(5, result.getPaginationInfo().getTotalResults());
        assertEquals(2, result.getPaginationInfo().getTotalPages());
    }

    @Test
    @DisplayName("Should filter tasks by status")
    void getAllTasks_Integration_FilterByStatus() {
        createTestTasks(3);
        completeFirstTask();

        PaginatedResponse<TaskResponseDTO> pendingTasks = taskService.getAllTasks(1, 10, null, 10, null, "desc");

        assertNotNull(pendingTasks);
        assertEquals(2, pendingTasks.getResult().size());
        pendingTasks.getResult().forEach(task -> {
            assertEquals(10, task.getStatusId());
            assertEquals("Pending", task.getStatusDescription());
        });

        PaginatedResponse<TaskResponseDTO> completedTasks = taskService.getAllTasks(1, 10, null, 20, null, "desc");

        assertNotNull(completedTasks);
        assertEquals(1, completedTasks.getResult().size());
        assertEquals(20, completedTasks.getResult().get(0).getStatusId());
        assertEquals("Completed", completedTasks.getResult().get(0).getStatusDescription());
    }

    @Test
    @DisplayName("Should filter tasks by active status")
    void getAllTasks_Integration_FilterByActiveStatus() {
        createTestTasks(3);
        deleteFirstTask();

        PaginatedResponse<TaskResponseDTO> activeTasks = taskService.getAllTasks(1, 10, null, null, true, "desc");

        assertNotNull(activeTasks);
        assertEquals(2, activeTasks.getResult().size());
        activeTasks.getResult().forEach(task -> assertEquals(true, task.getIsActive()));

        PaginatedResponse<TaskResponseDTO> inactiveTasks = taskService.getAllTasks(1, 10, null, null, false, "desc");

        assertNotNull(inactiveTasks);
        assertEquals(1, inactiveTasks.getResult().size());
        assertEquals(false, inactiveTasks.getResult().get(0).getIsActive());
    }

    @Test
    @DisplayName("Should complete task with database update")
    void completeTask_Integration_Success() {
        TaskEntity task = createSingleTestTask();
        Map<String, Object> updates = new HashMap<>();
        updates.put("statusId", 20);

        TaskResponseDTO result = taskService.completeTask(task.getUid(), updates);

        assertNotNull(result);
        assertEquals(task.getUid(), result.getUid());
        assertEquals(20, result.getStatusId());
        assertEquals("Completed", result.getStatusDescription());

        TaskEntity updatedEntity = taskRepository.findById(task.getUid()).orElse(null);
        assertNotNull(updatedEntity);
        assertEquals(20, updatedEntity.getStatusId().getStatusId());
        assertEquals("Completed", updatedEntity.getStatusId().getDescription());
    }

    @Test
    @DisplayName("Should delete task with database update")
    void deleteTask_Integration_Success() {
        TaskEntity task = createSingleTestTask();
        Map<String, Object> updates = new HashMap<>();
        updates.put("isActive", false);

        TaskResponseDTO result = taskService.deleteTask(task.getUid(), updates);

        assertNotNull(result);
        assertEquals(task.getUid(), result.getUid());
        assertEquals(false, result.getIsActive());

        TaskEntity updatedEntity = taskRepository.findById(task.getUid()).orElse(null);
        assertNotNull(updatedEntity);
        assertFalse(updatedEntity.getIsActive());
    }

    @Test
    @DisplayName("Should update task with database persistence")
    void updateTask_Integration_Success() {
        TaskEntity task = createSingleTestTask();
        CreateUpdateTaskDTO updateDTO = new CreateUpdateTaskDTO();
        updateDTO.setTitle("Updated Task Title");
        updateDTO.setDescription("Updated task description");

        TaskResponseDTO result = taskService.updateTask(task.getUid(), updateDTO);

        assertNotNull(result);
        assertEquals(task.getUid(), result.getUid());
        assertEquals("Updated Task Title", result.getTitle());
        assertEquals("Updated task description", result.getDescription());

        TaskEntity updatedEntity = taskRepository.findById(task.getUid()).orElse(null);
        assertNotNull(updatedEntity);
        assertEquals("Updated Task Title", updatedEntity.getTitle());
        assertEquals("Updated task description", updatedEntity.getDescription());
    }

    @Test
    @DisplayName("Should handle concurrent task operations")
    void concurrentOperations_Integration_Success() {
        createTestTasks(10);

        PaginatedResponse<TaskResponseDTO> allTasks = taskService.getAllTasks(1, 10, null, null, null, "desc");
        TaskResponseDTO firstTask = allTasks.getResult().get(0);

        Map<String, Object> completeUpdates = new HashMap<>();
        completeUpdates.put("statusId", 20);
        TaskResponseDTO completedTask = taskService.completeTask(firstTask.getUid(), completeUpdates);

        assertNotNull(completedTask);
        assertEquals(20, completedTask.getStatusId());
        assertEquals("Completed", completedTask.getStatusDescription());

        PaginatedResponse<TaskResponseDTO> pendingTasks = taskService.getAllTasks(1, 10, null, 10, null, "desc");
        assertTrue(pendingTasks.getResult().stream()
                .noneMatch(task -> task.getUid().equals(firstTask.getUid())));
    }

    // Helper methods
    private void createTestTasks(int count) {
        for (int i = 1; i <= count; i++) {
            TaskEntity task = new TaskEntity();
            task.setTitle("Test Task " + i);
            task.setDescription("Test Description " + i);
            task.setStatusId(pendingStatus);
            task.setIsActive(true);
            task.setCreatedBy("testUser");
            taskRepository.save(task);
        }
    }

    private TaskEntity createSingleTestTask() {
        TaskEntity task = new TaskEntity();
        task.setTitle("Single Test Task");
        task.setDescription("Single test description");
        task.setStatusId(pendingStatus);
        task.setIsActive(true);
        task.setCreatedBy("testUser");
        return taskRepository.save(task);
    }

    private void completeFirstTask() {
        TaskEntity firstTask = taskRepository.findAll().get(0);
        firstTask.setStatusId(completedStatus);
        taskRepository.save(firstTask);
    }

    private void deleteFirstTask() {
        TaskEntity firstTask = taskRepository.findAll().get(0);
        firstTask.setIsActive(false);
        taskRepository.save(firstTask);
    }
}
