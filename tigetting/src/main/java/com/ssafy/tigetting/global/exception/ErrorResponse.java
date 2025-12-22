package com.ssafy.tigetting.global.exception;

import java.time.LocalDateTime;

public class ErrorResponse {

    private final String code;
    private final String message;
    private final LocalDateTime timestamp;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.timestamp = LocalDateTime.now();
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
