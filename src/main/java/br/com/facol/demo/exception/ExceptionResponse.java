package br.com.facol.demo.exception;

import java.time.LocalDateTime;

public class ExceptionResponse {
    private String message;
    private String code;
    private LocalDateTime timestamp;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
