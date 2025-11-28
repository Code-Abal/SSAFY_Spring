package com.ssafy.tigetting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Refund {

    private Long refundId;

    private Payment payment;

    private Booking booking;

    @Builder.Default
    private BigDecimal refundAmount = BigDecimal.ZERO;

    private String reason;

    @Builder.Default
    private RefundStatus status = RefundStatus.REQUESTED;

    private LocalDateTime requestedAt;

    private LocalDateTime processedAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public enum RefundStatus {
        REQUESTED, PROCESSING, COMPLETED, REJECTED
    }
}