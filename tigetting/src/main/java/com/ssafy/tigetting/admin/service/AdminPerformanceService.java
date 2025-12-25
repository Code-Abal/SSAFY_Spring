package com.ssafy.tigetting.admin.service;

import com.ssafy.tigetting.mapper.AdminPerformanceMapper;
import com.ssafy.tigetting.performance.dto.PerformanceDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 관리자 공연 관리 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminPerformanceService {

    private final AdminPerformanceMapper adminPerformanceMapper;

    /**
     * 예정된 공연 조회 (공연 시작일 기준 최신순)
     */
    public List<PerformanceDto> getUpcomingPerformances(int limit) {
        return adminPerformanceMapper.getUpcomingPerformances(limit);
    }

    /**
     * 공연 목록 조회 (페이징, 검색, 필터링)
     */
    public Map<String, Object> getPerformanceList(int page, int size, String search, Integer genreId, String area,
            String prfstate) {
        int offset = page * size;

        List<PerformanceDto> performances = adminPerformanceMapper.getPerformanceList(offset, size, search, genreId,
                area, prfstate);
        int total = adminPerformanceMapper.getPerformanceCount(search, genreId, area, prfstate);

        Map<String, Object> result = new HashMap<>();
        result.put("performances", performances);
        result.put("pagination", Map.of(
                "currentPage", page,
                "totalPages", (int) Math.ceil((double) total / size),
                "totalItems", total,
                "itemsPerPage", size));

        return result;
    }

    /**
     * 공연 상세 조회
     */
    public Map<String, Object> getPerformanceDetail(String mt20id) {
        Map<String, Object> performance = adminPerformanceMapper.getPerformanceById(mt20id);
        if (performance == null) {
            throw new RuntimeException("공연을 찾을 수 없습니다: " + mt20id);
        }

        Map<String, Object> details = adminPerformanceMapper.getPerformanceDetails(mt20id);
        List<String> styurls = adminPerformanceMapper.getPerformanceStyurls(mt20id);

        Map<String, Object> result = new HashMap<>();
        result.put("performance", performance);
        result.put("details", details);
        result.put("styurls", styurls);

        return result;
    }

    /**
     * 공연 수정
     */
    @Transactional
    public void updatePerformance(String mt20id, Map<String, Object> performanceData) {
        // 공연 존재 확인
        Map<String, Object> existing = adminPerformanceMapper.getPerformanceById(mt20id);
        if (existing == null) {
            throw new RuntimeException("공연을 찾을 수 없습니다: " + mt20id);
        }

        // 공연 정보 업데이트
        adminPerformanceMapper.updatePerformance(mt20id, performanceData);
    }

    /**
     * 공연 삭제
     */
    @Transactional
    public void deletePerformance(String mt20id) {
        Map<String, Object> existing = adminPerformanceMapper.getPerformanceById(mt20id);
        if (existing == null) {
            throw new RuntimeException("공연을 찾을 수 없습니다: " + mt20id);
        }

        adminPerformanceMapper.deletePerformance(mt20id);
    }
}
