package com.lakshancd.todo_application_backend.payload.response.error;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;


@Getter
@Setter
@Builder
@ToString
public class GlobalErrorResponse {
    private HttpStatus status;
    private String exceptionCode;
    private String error;
}
