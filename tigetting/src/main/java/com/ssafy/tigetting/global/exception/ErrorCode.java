package com.ssafy.tigetting.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // AUTH
    AUTH_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_001", "이메일 또는 비밀번호가 올바르지 않습니다"),
    ADMIN_ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH_002", "관리자 권한이 필요합니다"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH_003", "유효하지 않은 토큰입니다"),
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "AUTH_004", "이미 가입된 이메일입니다"),

    // USER
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_005", "존재하지 않는 사용자입니다"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "USER_006", "비밀번호가 일치하지 않습니다"),

    // COMMON
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_001", "잘못된 요청입니다"),
    INTERNAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON_500", "서버 오류가 발생했습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;

    ErrorCode(HttpStatus status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
