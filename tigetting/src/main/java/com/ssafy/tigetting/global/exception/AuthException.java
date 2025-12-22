package com.ssafy.tigetting.global.exception;

public class AuthException extends BusinessException {

    public AuthException() {
        super(ErrorCode.AUTH_FAILED);
    }

    public AuthException(ErrorCode errorCode) {
        super(errorCode);
    }
}
