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
public class SystemMetric {

    private Long metricId;

    private LocalDateTime timestamp;

    @Builder.Default
    private Integer activeUsers = 0;

    @Builder.Default
    private Integer queueLength = 0;

    @Builder.Default
    private BigDecimal cpuUsage = BigDecimal.ZERO;

    @Builder.Default
    private BigDecimal memoryUsage = BigDecimal.ZERO;

    @Builder.Default
    private Integer requestCount = 0;

    @Builder.Default
    private BigDecimal avgResponseTime = BigDecimal.ZERO;

    private String serverId;

    private LocalDateTime createdAt;
}