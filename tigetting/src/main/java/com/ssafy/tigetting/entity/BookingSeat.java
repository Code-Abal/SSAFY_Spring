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
public class BookingSeat {

    private Long bookingSeatId;

    private Booking booking;

    private ScheduleSeat seat;

    @Builder.Default
    private BigDecimal seatPrice = BigDecimal.ZERO;

    private LocalDateTime createdAt;
}