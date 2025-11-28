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
public class ScheduleSeat {

    private Long seatId;

    private PerformanceSchedule schedule;

    private VenueSeat venueSeat;

    @Builder.Default
    private BigDecimal price = BigDecimal.ZERO;

    @Builder.Default
    private SeatStatus status = SeatStatus.AVAILABLE;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<BookingSeat> bookingSeats;

    private List<SeatLock> seatLocks;

    public enum SeatStatus {
        AVAILABLE, LOCKED, BOOKED
    }
}