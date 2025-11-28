package com.ssafy.tigetting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PerformanceSchedule {

    private Long scheduleId;

    private Performance performance;

    private LocalDateTime showDatetime;

    @Builder.Default
    private Integer totalSeats = 0;

    @Builder.Default
    private Integer availableSeats = 0;

    @Builder.Default
    private ScheduleStatus status = ScheduleStatus.OPEN;

    private LocalDateTime bookingStartAt;

    private LocalDateTime bookingEndAt;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<ScheduleSeat> seats;

    private List<Booking> bookings;

    public enum ScheduleStatus {
        OPEN, CLOSED, SOLDOUT
    }
}