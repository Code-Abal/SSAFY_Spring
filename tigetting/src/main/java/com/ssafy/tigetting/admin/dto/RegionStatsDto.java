package com.ssafy.tigetting.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 지역별 통계 DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegionStatsDto {

    private String area;
    private Integer count;
}
