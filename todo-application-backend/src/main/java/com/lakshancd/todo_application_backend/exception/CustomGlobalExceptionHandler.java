package com.lakshancd.todo_application_backend.exception;

import com.lakshancd.todo_application_backend.enums.ExceptionCode;
import com.lakshancd.todo_application_backend.payload.response.error.ErrorDetails;
import com.lakshancd.todo_application_backend.payload.response.error.GlobalErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomGlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId"+correlationId+", Field -- " + ex.getBindingResult().getFieldError().getField() + " : Message --" + ex.getBindingResult().getFieldError().getDefaultMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.PRECONDITION_FAILED)
                .exceptionCode(ExceptionCode.FILED_VALIDATION.getCode())
                .error(ex.getBindingResult().getFieldError().getDefaultMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BaseRuntimeException.class)
    public ResponseEntity<Object> handleValidationException(BaseRuntimeException baseRuntimeException) {
        ErrorDetails error = baseRuntimeException.getErrorDetails();
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId"+correlationId+", BusinessFieldValidationException {}", error.getErrorMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionCode(String.valueOf(error.getExceptionCode().getCode()))
                .error(error.getErrorMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleResourceNotFountException(Exception ex) {
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId"+correlationId+" , An unexpected error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .exceptionCode(ExceptionCode.INTERNAL_SERVER_ERROR.getCode())
                .error(ex.getMessage())
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId"+correlationId+" ,Illegal Argument Exception: {}", ex.getMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST)
                .exceptionCode(ExceptionCode.EXPECTED_VALIDATION_CODE.getCode())
                .error(ex.getMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> dataNotFoundException(DataNotFoundException ex) {
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId"+correlationId+" ,DataNotFoundException: {}", ex.getMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.NOT_FOUND)
                .exceptionCode(ExceptionCode.EXPECTED_VALIDATION_CODE.getCode())
                .error(ex.getErrorDetails().getErrorMessage())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<GlobalErrorResponse> handleDataAccessException(DataAccessException ex) {
        String correlationId = MDC.get("CORRELATION-ID");
        log.error("CorrelationId{} , Database Exception: {}", correlationId, ex.getMessage());
        return new ResponseEntity<>(GlobalErrorResponse
                .builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .exceptionCode(ExceptionCode.DATABASE_ERROR.getCode())
                .error("A database error occurred. Please try again later.")
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
