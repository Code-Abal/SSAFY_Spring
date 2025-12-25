package com.ssafy.tigetting.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 전체 통계 응답 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminStatsResponse {

    private Integer totalPerformances;
    private Integer totalVenues;
    private Integer totalUsers;
    private Integer totalReservations;
    private Integer todayReservations;
}
