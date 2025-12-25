package com.ssafy.tigetting.admin.controller;

import com.ssafy.tigetting.admin.dto.ApiResponse;
import com.ssafy.tigetting.admin.service.AdminPerformanceService;
import com.ssafy.tigetting.performance.dto.PerformanceDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 관리자 공연 관리 API 컨트롤러
 */
@Tag(name = "Admin Performances", description = "관리자 공연 관리 API")
@RestController
@RequestMapping("/api/admin/performances")
@RequiredArgsConstructor
public class AdminPerformanceController {

        private final AdminPerformanceService adminPerformanceService;

        /**
         * 예정된 공연 조회 (공연 시작일 기준 최신순)
         */
        @Operation(summary = "예정된 공연 조회", description = "공연 시작일이 늦은 순서로 정렬된 공연 목록 조회")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
        })
        @GetMapping("/recent")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ApiResponse<List<PerformanceDto>>> getUpcomingPerformances(
                        @Parameter(description = "조회 개수 (기본값: 5, 최대: 20)") @RequestParam(defaultValue = "5") int limit) {
                // limit 최대값 제한
                if (limit > 20) {
                        limit = 20;
                }

                List<PerformanceDto> performances = adminPerformanceService.getUpcomingPerformances(limit);
                return ResponseEntity.ok(ApiResponse.success(performances));
        }

        /**
         * 공연 목록 조회 (페이징, 검색, 필터링)
         */
        @Operation(summary = "공연 목록 조회", description = "페이징, 검색, 필터링이 가능한 공연 목록 조회")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
        })
        @GetMapping
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ApiResponse<Map<String, Object>>> getPerformanceList(
                        @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
                        @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "20") int size,
                        @Parameter(description = "검색어 (공연명)") @RequestParam(required = false) String search,
                        @Parameter(description = "장르 ID") @RequestParam(required = false) Integer genreId,
                        @Parameter(description = "지역") @RequestParam(required = false) String area,
                        @Parameter(description = "공연 상태") @RequestParam(required = false) String prfstate) {
                Map<String, Object> result = adminPerformanceService.getPerformanceList(page, size, search, genreId,
                                area,
                                prfstate);
                return ResponseEntity.ok(ApiResponse.success(result));
        }

        /**
         * 공연 수정
         */
        @Operation(summary = "공연 수정", description = "공연 정보 수정")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "수정 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연을 찾을 수 없음"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
        })
        @PutMapping("/{mt20id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ApiResponse<Void>> updatePerformance(
                        @Parameter(description = "공연 ID") @PathVariable String mt20id,
                        @RequestBody Map<String, Object> performanceData) {
                adminPerformanceService.updatePerformance(mt20id, performanceData);
                return ResponseEntity.ok(ApiResponse.success(null));
        }

        /**
         * 공연 삭제
         */
        @Operation(summary = "공연 삭제", description = "공연 삭제")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "삭제 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연을 찾을 수 없음"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
        })
        @DeleteMapping("/{mt20id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ApiResponse<Void>> deletePerformance(
                        @Parameter(description = "공연 ID") @PathVariable String mt20id) {
                adminPerformanceService.deletePerformance(mt20id);
                return ResponseEntity.ok(ApiResponse.success(null));
        }

        /**
         * 공연 상세 조회
         */
        @Operation(summary = "공연 상세 조회", description = "공연 ID로 상세 정보 조회")
        @ApiResponses({
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연을 찾을 수 없음"),
                        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
        })
        @GetMapping("/{mt20id}")
        @PreAuthorize("hasRole('ADMIN')")
        public ResponseEntity<ApiResponse<Map<String, Object>>> getPerformanceDetail(
                        @Parameter(description = "공연 ID") @PathVariable String mt20id) {
                Map<String, Object> performance = adminPerformanceService.getPerformanceDetail(mt20id);
                return ResponseEntity.ok(ApiResponse.success(performance));
        }
}
