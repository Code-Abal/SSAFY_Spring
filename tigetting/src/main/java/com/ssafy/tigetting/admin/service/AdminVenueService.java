package com.ssafy.tigetting.admin.service;

import com.ssafy.tigetting.mapper.AdminVenueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 관리자 공연장 관리 서비스
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AdminVenueService {

    private final AdminVenueMapper adminVenueMapper;

    /**
     * 공연장 목록 조회 (페이징, 검색, 필터링)
     */
    public Map<String, Object> getVenueList(int page, int size, String search, String region) {
        int offset = page * size;

        List<Map<String, Object>> venues = adminVenueMapper.getVenueList(offset, size, search, region);
        int total = adminVenueMapper.getVenueCount(search, region);

        Map<String, Object> result = new HashMap<>();
        result.put("venues", venues);
        result.put("pagination", Map.of(
                "currentPage", page,
                "totalPages", (int) Math.ceil((double) total / size),
                "totalItems", total,
                "itemsPerPage", size));

        return result;
    }

    /**
     * 공연장 상세 조회
     */
    public Map<String, Object> getVenueDetail(String mt10id) {
        Map<String, Object> venue = adminVenueMapper.getVenueById(mt10id);
        if (venue == null) {
            throw new RuntimeException("공연장을 찾을 수 없습니다: " + mt10id);
        }

        // 상세 정보 조회
        Map<String, Object> details = adminVenueMapper.getVenueDetails(mt10id);

        Map<String, Object> result = new HashMap<>();
        result.put("venue", venue);
        result.put("details", details);

        return result;
    }

    /**
     * 공연장 수정
     */
    @Transactional
    public void updateVenue(String mt10id, Map<String, Object> venueData) {
        Map<String, Object> existing = adminVenueMapper.getVenueById(mt10id);
        if (existing == null) {
            throw new RuntimeException("공연장을 찾을 수 없습니다: " + mt10id);
        }

        adminVenueMapper.updateVenue(mt10id, venueData);
    }

    /**
     * 공연장 삭제
     */
    @Transactional
    public void deleteVenue(String mt10id) {
        Map<String, Object> existing = adminVenueMapper.getVenueById(mt10id);
        if (existing == null) {
            throw new RuntimeException("공연장을 찾을 수 없습니다: " + mt10id);
        }

        // 해당 공연장을 사용하는 공연이 있는지 확인
        int performanceCount = adminVenueMapper.getPerformanceCountByVenue(mt10id);
        if (performanceCount > 0) {
            throw new RuntimeException("이 공연장을 사용하는 공연이 " + performanceCount + "개 있어 삭제할 수 없습니다.");
        }

        adminVenueMapper.deleteVenue(mt10id);
    }
}
