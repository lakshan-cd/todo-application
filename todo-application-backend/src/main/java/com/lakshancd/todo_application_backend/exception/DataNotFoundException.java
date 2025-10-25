package com.lakshancd.todo_application_backend.exception;

import com.lakshancd.todo_application_backend.payload.response.error.ErrorDetails;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class DataNotFoundException extends RuntimeException{
    private final ErrorDetails errorDetails;
    private Object data;

    public DataNotFoundException(final ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
    }
    public DataNotFoundException(final ErrorDetails errorDetails, final Object data) {
        this.data = data;
        this.errorDetails = errorDetails;
    }
}
