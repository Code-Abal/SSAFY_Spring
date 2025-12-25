package com.ssafy.tigetting.global.exception;

import org.springframework.http.HttpStatus;

public enum ErrorCode {
    // AUTH - 인증/인가 관련
    AUTH_FAILED(HttpStatus.UNAUTHORIZED, "AUTH_001", "이메일 또는 비밀번호가 올바르지 않습니다"),
    ADMIN_ACCESS_DENIED(HttpStatus.FORBIDDEN, "AUTH_002", "관리자 권한이 필요합니다"),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "AUTH_003", "유효하지 않은 토큰입니다"),
    EMAIL_DUPLICATED(HttpStatus.BAD_REQUEST, "AUTH_004", "이미 가입된 이메일입니다"),

    // PERFORMANCE - 공연 관련
    PERFORMANCE_NOT_FOUND(HttpStatus.NOT_FOUND, "PERFORMANCE_001", "해당 공연을 찾을 수 없습니다"),
    PERFORMANCE_CREATION_FAILED(HttpStatus.BAD_REQUEST, "PERFORMANCE_002", "공연 생성에 실패했습니다"),
    PERFORMANCE_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "PERFORMANCE_003", "공연 업데이트에 실패했습니다"),
    IMAGE_PROCESSING_FAILED(HttpStatus.BAD_REQUEST, "PERFORMANCE_004", "이미지 처리 중 오류가 발생했습니다"),
    POSTER_NOT_FOUND(HttpStatus.NOT_FOUND, "PERFORMANCE_005", "포스터를 찾을 수 없습니다"),

    // USER - 사용자 관련
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER_001", "존재하지 않는 사용자입니다"),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "USER_002", "비밀번호가 일치하지 않습니다"),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "USER_003", "현재 비밀번호가 올바르지 않습니다"),
    USER_FORBIDDEN(HttpStatus.FORBIDDEN, "USER_004", "본인만 수정할 수 있습니다"),

    // RESERVATION - 예약 관련
    RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "RESERVATION_001", "예약 정보를 찾을 수 없습니다"),
    RESERVATION_SEAT_ALREADY_BOOKED(HttpStatus.BAD_REQUEST, "RESERVATION_002", "이미 예약된 좌석입니다"),
    RESERVATION_ALREADY_CANCELLED(HttpStatus.CONFLICT, "RESERVATION_003", "이미 취소된 예약입니다"),
    RESERVATION_SEAT_LOCKED(HttpStatus.LOCKED, "RESERVATION_004", "다른 사용자가 좌석을 선택 중입니다"),

    // PAYMENT - 결제 관련
    PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PAYMENT_001", "결제 정보를 찾을 수 없습니다"),
    PAYMENT_AMOUNT_MISMATCH(HttpStatus.BAD_REQUEST, "PAYMENT_002", "결제 금액이 일치하지 않습니다"),
    PAYMENT_FAILED(HttpStatus.PAYMENT_REQUIRED, "PAYMENT_003", "결제 처리 중 오류가 발생했습니다"),
    PAYMENT_TIMEOUT(HttpStatus.REQUEST_TIMEOUT, "PAYMENT_004", "결제 시간이 초과되었습니다"),

    // REFUND - 환불 관련
    REFUND_NOT_FOUND(HttpStatus.NOT_FOUND, "REFUND_001", "환불 요청을 찾을 수 없습니다"),
    REFUND_PERIOD_EXPIRED(HttpStatus.BAD_REQUEST, "REFUND_002", "환불 가능 기간이 지났습니다"),
    REFUND_ALREADY_PROCESSED(HttpStatus.CONFLICT, "REFUND_003", "이미 환불 처리된 예약입니다"),

    // VENUE - 공연장 관련
    VENUE_NOT_FOUND(HttpStatus.NOT_FOUND, "VENUE_001", "공연장을 찾을 수 없습니다"),
    VENUE_SEAT_INFO_INVALID(HttpStatus.BAD_REQUEST, "VENUE_002", "공연장 좌석 정보가 올바르지 않습니다"),

    // SEAT - 좌석 관련
    SEAT_NOT_FOUND(HttpStatus.NOT_FOUND, "SEAT_001", "좌석을 찾을 수 없습니다"),
    SEAT_ALREADY_SOLD(HttpStatus.GONE, "SEAT_002", "이미 판매된 좌석입니다"),
    SEAT_LOCKED(HttpStatus.LOCKED, "SEAT_003", "좌석이 일시적으로 잠겨있습니다"),

    // QUEUE - 대기열 관련
    QUEUE_TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "QUEUE_001", "대기열 토큰을 찾을 수 없습니다"),
    QUEUE_TOKEN_EXPIRED(HttpStatus.GONE, "QUEUE_002", "대기열 토큰이 만료되었습니다"),
    QUEUE_WAITING(HttpStatus.TOO_MANY_REQUESTS, "QUEUE_003", "대기 중입니다. 잠시 후 다시 시도해주세요"),

    // EMAIL - 이메일 관련
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "EMAIL_001", "이메일 발송에 실패했습니다"),
    EMAIL_CODE_MISMATCH(HttpStatus.BAD_REQUEST, "EMAIL_002", "인증 코드가 일치하지 않습니다"),
    EMAIL_CODE_EXPIRED(HttpStatus.GONE, "EMAIL_003", "인증 코드가 만료되었습니다"),

    // COMMON - 공통
    INVALID_REQUEST(HttpStatus.BAD_REQUEST, "COMMON_001", "잘못된 요청입니다"),
    MISSING_PARAMETER(HttpStatus.BAD_REQUEST, "COMMON_002", "필수 파라미터가 누락되었습니다"),
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
