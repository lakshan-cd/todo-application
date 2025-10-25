package com.lakshancd.todo_application_backend.entity.logs;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DataEntity {
    private String correlationId;
    private String sessionId;
    private String workflowId;
}
