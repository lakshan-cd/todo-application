package com.lakshancd.todo_application_backend.payload.response.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class ResponseHolder<T> {
    private String code;
    private String message;
    private T data;

    public ResponseHolder(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

