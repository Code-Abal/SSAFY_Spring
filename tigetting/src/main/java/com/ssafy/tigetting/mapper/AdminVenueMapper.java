package com.ssafy.tigetting.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 관리자 공연장 관리 Mapper
 */
@Mapper
public interface AdminVenueMapper {

        /**
         * 공연장 목록 조회 (페이징, 검색, 필터링)
         */
        List<Map<String, Object>> getVenueList(
                        @Param("offset") int offset,
                        @Param("size") int size,
                        @Param("search") String search,
                        @Param("region") String region);

        /**
         * 공연장 총 개수 (필터링 적용)
         */
        int getVenueCount(
                        @Param("search") String search,
                        @Param("region") String region);

        /**
         * 공연장 ID로 기본 정보 조회
         */
        Map<String, Object> getVenueById(@Param("mt10id") String mt10id);

        /**
         * 공연장 상세 정보 조회 (venue_details) /**
         * 공연장의 상세 정보 조회
         */
        Map<String, Object> getVenueDetails(@Param("mt10id") String mt10id);

        /**
         * 공연장 정보 수정
         */
        void updateVenue(
                        @Param("mt10id") String mt10id,
                        @Param("data") Map<String, Object> data);

        /**
         * 공연장 삭제
         */
        void deleteVenue(@Param("mt10id") String mt10id);

        /**
         * 공연장을 사용하는 공연 개수 조회
         */
        int getPerformanceCountByVenue(@Param("mt10id") String mt10id);
}
