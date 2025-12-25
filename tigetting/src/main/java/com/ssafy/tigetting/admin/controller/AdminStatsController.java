package com.ssafy.tigetting.admin.controller;

import com.ssafy.tigetting.admin.dto.AdminStatsResponse;
import com.ssafy.tigetting.admin.dto.ApiResponse;
import com.ssafy.tigetting.admin.dto.CategoryStatsDto;
import com.ssafy.tigetting.admin.dto.RegionStatsDto;
import com.ssafy.tigetting.admin.service.AdminStatsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 관리자 통계 API 컨트롤러
 */
@Tag(name = "Admin Stats", description = "관리자 대시보드 통계 API")
@RestController
@RequestMapping("/api/admin/stats")
@RequiredArgsConstructor
public class AdminStatsController {

    private final AdminStatsService adminStatsService;

    /**
     * 전체 통계 조회
     */
    @Operation(summary = "전체 통계 조회", description = "관리자 대시보드에 표시할 전체 통계 데이터 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<AdminStatsResponse>> getOverallStats() {
        AdminStatsResponse stats = adminStatsService.getOverallStats();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 카테고리별 공연 통계
     */
    @Operation(summary = "카테고리별 통계 조회", description = "장르별 공연 수 및 비율")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @GetMapping("/categories")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<CategoryStatsDto>>> getCategoryStats() {
        List<CategoryStatsDto> stats = adminStatsService.getCategoryStats();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }

    /**
     * 지역별 공연 통계
     */
    @Operation(summary = "지역별 통계 조회", description = "지역별 공연 수")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @GetMapping("/regions")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<List<RegionStatsDto>>> getRegionStats() {
        List<RegionStatsDto> stats = adminStatsService.getRegionStats();
        return ResponseEntity.ok(ApiResponse.success(stats));
    }
}
