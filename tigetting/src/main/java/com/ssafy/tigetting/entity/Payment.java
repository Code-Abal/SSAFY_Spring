package com.ssafy.tigetting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    private Long paymentId;

    private Booking booking;

    private String transactionId;

    @Builder.Default
    private BigDecimal amount = BigDecimal.ZERO;

    private PaymentMethod paymentMethod;

    @Builder.Default
    private PaymentStatus status = PaymentStatus.PENDING;

    private String pgResponse;

    private LocalDateTime paidAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Refund> refunds;

    public enum PaymentMethod {
        CARD, BANK, KAKAO_PAY, NAVER_PAY, TOSS_PAY
    }

    public enum PaymentStatus {
        PENDING, COMPLETED, FAILED
    }
}