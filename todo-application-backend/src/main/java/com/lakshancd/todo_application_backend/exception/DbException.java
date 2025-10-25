package com.lakshancd.todo_application_backend.exception;

public class DbException extends RuntimeException{
    public DbException(String message, Exception e) {
        super(message , e);
    }
}
