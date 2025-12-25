package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.admin.dto.AdminStatsResponse;
import com.ssafy.tigetting.admin.dto.CategoryStatsDto;
import com.ssafy.tigetting.admin.dto.RegionStatsDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 관리자 통계 Mapper
 */
@Mapper
public interface AdminStatsMapper {

    /**
     * 전체 통계 조회
     */
    AdminStatsResponse getOverallStats();

    /**
     * 카테고리별 공연 통계
     */
    List<CategoryStatsDto> getCategoryStats();

    /**
     * 지역별 공연 통계
     */
    List<RegionStatsDto> getRegionStats();
}
