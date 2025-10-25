package com.lakshancd.todo_application_backend.contoller.task;

import com.lakshancd.todo_application_backend.common.constants.ApiVersion;
import com.lakshancd.todo_application_backend.common.constants.Paths;
import com.lakshancd.todo_application_backend.common.constants.ResponseConstants;
import com.lakshancd.todo_application_backend.dto.task.CreateUpdateTaskDTO;
import com.lakshancd.todo_application_backend.dto.task.TaskResponseDTO;
import com.lakshancd.todo_application_backend.payload.response.pagination.PaginatedResponse;
import com.lakshancd.todo_application_backend.payload.response.support.ResponseBuilder;
import com.lakshancd.todo_application_backend.payload.response.support.ResponseHolder;
import com.lakshancd.todo_application_backend.service.task.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin
@RestController
@Tag(name = "Task", description = "Task management APIs")
@Slf4j
@RequiredArgsConstructor
public class TaskController {
    private static final String BasePath = Paths.PROJECT_BASE_PATH + ApiVersion.API_VERSION + "/task";
    private final TaskService taskService;

    @Operation(summary = "Create a Task" , description = "API to create a new task")
    @PostMapping(BasePath)
    public ResponseEntity<ResponseHolder<TaskResponseDTO>> createAccount(@RequestBody @Valid CreateUpdateTaskDTO request) {

        TaskResponseDTO result = taskService.createTask(request);
        return ResponseBuilder.builder(result)
                .status(HttpStatus.CREATED)
                .code(ResponseConstants.CommonMessages.CREATED[1])
                .message(ResponseConstants.getCreatedMessage(ResponseConstants.TASK))
                .build();
    }

    @Operation(summary = "Get All Tasks" , description = "API to get all tasks")
    @GetMapping(BasePath)
    public ResponseEntity<ResponseHolder<PaginatedResponse<TaskResponseDTO>>> getAllReceiptsData(@RequestParam(defaultValue = "1") Integer page,
                                                                                                 @RequestParam(defaultValue = "5") Integer pageSize,
                                                                                                 @RequestParam(required = false) String title,
                                                                                                 @RequestParam(required = false) Integer statusId,
                                                                                                 @RequestParam(required = false) Boolean isActive,
                                                                                                 @RequestParam(required = false) String sortDirection
    )
    {
        PaginatedResponse<TaskResponseDTO> result = taskService.getAllTasks(page,pageSize,title,statusId, isActive, sortDirection);

        return ResponseBuilder.builder(result)
                .status(HttpStatus.OK)
                .code(ResponseConstants.CommonMessages.FETCHED[1])
                .message(ResponseConstants.getFetchedMessage(ResponseConstants.TASK))
                .build();
    }

    @Operation(summary = "Get Task By Id" , description = "API to get a task by Id")
    @GetMapping(BasePath + "/{id}")
    public ResponseEntity<ResponseHolder<TaskResponseDTO>> getTaskById(@PathVariable Integer id) {
        TaskResponseDTO result = taskService.getTaskById(id);
        return ResponseBuilder.builder(result)
                .status(HttpStatus.OK)
                .code(ResponseConstants.CommonMessages.FETCHED[1])
                .message(ResponseConstants.getFetchedMessage(ResponseConstants.TASK))
                .build();
    }

    @Operation(summary = "Update Task" , description = "API to update a task")
    @PutMapping(BasePath +  "/{id}")
    public ResponseEntity<ResponseHolder<TaskResponseDTO>> updateTask(@PathVariable Integer id, @RequestBody @Valid CreateUpdateTaskDTO request) {
        TaskResponseDTO result = taskService.updateTask(id,request);

        return ResponseBuilder.builder(result)
                .status(HttpStatus.CREATED)
                .code(ResponseConstants.CommonMessages.UPDATED[1])
                .message(ResponseConstants.getUpdatedMessage(ResponseConstants.TASK))
                .build();
    }

    @Operation(summary = "Complete Task" , description = "API to complete a task")
    @PatchMapping(BasePath+"/{id}/complete")
    public ResponseEntity<ResponseHolder<TaskResponseDTO>> completeTask(@PathVariable Integer id , @RequestBody Map<String,Object> updates){
        TaskResponseDTO patched = taskService.completeTask(id, updates);

        return ResponseBuilder.builder(patched)
                .status(HttpStatus.OK)
                .code(ResponseConstants.CommonMessages.PATCHED[1])
                .message(ResponseConstants.getUpdatedMessage(ResponseConstants.STATUS))
                .build();
    }

    @Operation(summary = "Delete Task" , description = "API to delete a task")
    @DeleteMapping(BasePath+"/{id}/delete")
    public ResponseEntity<ResponseHolder<TaskResponseDTO>> deleteTask(@PathVariable Integer id , @RequestBody Map<String,Object> updates){
        TaskResponseDTO patched = taskService.deleteTask(id, updates);

        return ResponseBuilder.builder(patched)
                .status(HttpStatus.OK)
                .code(ResponseConstants.CommonMessages.DELETED[1])
                .message(ResponseConstants.getDeleteMessage(ResponseConstants.STATUS))
                .build();
    }


}
