package com.lakshancd.todo_application_backend.payload.response.error;

import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class ErrorDetails {
    private ExceptionCode exceptionCode;
    private String errorMessage;

}
