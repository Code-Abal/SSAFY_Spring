package com.ssafy.tigetting.admin.controller;

import com.ssafy.tigetting.admin.dto.ApiResponse;
import com.ssafy.tigetting.admin.service.AdminVenueService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 관리자 공연장 관리 API 컨트롤러
 */
@Tag(name = "Admin Venues", description = "관리자 공연장 관리 API")
@RestController
@RequestMapping("/api/admin/venues")
@RequiredArgsConstructor
public class AdminVenueController {

    private final AdminVenueService adminVenueService;

    /**
     * 공연장 목록 조회 (페이징, 검색, 필터링)
     */
    @Operation(summary = "공연장 목록 조회", description = "페이징, 검색, 필터링이 가능한 공연장 목록 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getVenueList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "검색어 (공연장명)") @RequestParam(required = false) String search,
            @Parameter(description = "권역") @RequestParam(required = false) String region) {
        Map<String, Object> result = adminVenueService.getVenueList(page, size, search, region);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 공연장 수정
     */
    @Operation(summary = "공연장 수정", description = "공연장 정보 수정")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "수정 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연장을 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @PutMapping("/{mt10id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> updateVenue(
            @Parameter(description = "공연장 ID") @PathVariable String mt10id,
            @RequestBody Map<String, Object> venueData) {
        adminVenueService.updateVenue(mt10id, venueData);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 공연장 삭제
     */
    @Operation(summary = "공연장 삭제", description = "공연장 삭제")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "삭제 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연장을 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @DeleteMapping("/{mt10id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteVenue(
            @Parameter(description = "공연장 ID") @PathVariable String mt10id) {
        adminVenueService.deleteVenue(mt10id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 공연장 상세 조회
     */
    @Operation(summary = "공연장 상세 조회", description = "공연장 ID로 상세 정보 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "조회 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "공연장을 찾을 수 없음"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "403", description = "관리자 권한 필요")
    })
    @GetMapping("/{mt10id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getVenueDetail(
            @Parameter(description = "공연장 ID") @PathVariable String mt10id) {
        Map<String, Object> venue = adminVenueService.getVenueDetail(mt10id);
        return ResponseEntity.ok(ApiResponse.success(venue));
    }
}
