package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.performance.dto.PerformanceDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 관리자 공연 관리 Mapper
 */
@Mapper
public interface AdminPerformanceMapper {

        /**
         * 예정된 공연 조회 (공연 시작일 기준 최신순)
         */
        List<PerformanceDto> getUpcomingPerformances(@Param("limit") int limit);

        /**
         * 공연 목록 조회 (페이징, 검색, 필터링)
         */
        List<PerformanceDto> getPerformanceList(
                        @Param("offset") int offset,
                        @Param("size") int size,
                        @Param("search") String search,
                        @Param("genreId") Integer genreId,
                        @Param("area") String area,
                        @Param("prfstate") String prfstate);

        /**
         * 공연 총 개수 (필터링 적용)
         */
        int getPerformanceCount(
                        @Param("search") String search,
                        @Param("genreId") Integer genreId,
                        @Param("area") String area,
                        @Param("prfstate") String prfstate);

        /**
         * 공연 ID로 기본 정보 조회
         */
        Map<String, Object> getPerformanceById(@Param("mt20id") String mt20id);

        /**
         * 공연 상세 정보 조회 (performance_details)
         */
        Map<String, Object> getPerformanceDetails(@Param("mt20id") String mt20id);

        /**
         * 공연 스틸 이미지 URL 조회
         */
        List<String> getPerformanceStyurls(@Param("mt20id") String mt20id);

        /**
         * 공연 정보 수정
         */
        void updatePerformance(
                        @Param("mt20id") String mt20id,
                        @Param("data") Map<String, Object> data);

        /**
         * 공연 삭제
         */
        void deletePerformance(@Param("mt20id") String mt20id);
}
