package com.lakshancd.todo_application_backend.service.task;

import com.lakshancd.todo_application_backend.dto.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.dto.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginatedResponse;

import java.util.Map;

public interface TaskService {
    TaskResponseDTO createTask(CreateUpdateTaskDTO request);

    PaginatedResponse<TaskResponseDTO> getAllTasks(Integer page, Integer pageSize, String title, Integer statusId, Boolean isActive, String sortDirection);

    TaskResponseDTO getTaskById(Integer id);

    TaskResponseDTO completeTask(Integer id, Map<String, Object> updates);

    TaskResponseDTO deleteTask(Integer id, Map<String, Object> updates);

    TaskResponseDTO updateTask(Integer id, CreateUpdateTaskDTO request);
}
