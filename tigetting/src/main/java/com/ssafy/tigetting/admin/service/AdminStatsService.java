package com.ssafy.tigetting.admin.service;

import com.ssafy.tigetting.admin.dto.AdminStatsResponse;
import com.ssafy.tigetting.admin.dto.CategoryStatsDto;
import com.ssafy.tigetting.admin.dto.RegionStatsDto;
import com.ssafy.tigetting.mapper.AdminStatsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 관리자 통계 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminStatsService {

    private final AdminStatsMapper adminStatsMapper;

    /**
     * 전체 통계 조회
     */
    public AdminStatsResponse getOverallStats() {
        return adminStatsMapper.getOverallStats();
    }

    /**
     * 카테고리별 공연 통계
     */
    public List<CategoryStatsDto> getCategoryStats() {
        return adminStatsMapper.getCategoryStats();
    }

    /**
     * 지역별 공연 통계
     */
    public List<RegionStatsDto> getRegionStats() {
        return adminStatsMapper.getRegionStats();
    }
}
