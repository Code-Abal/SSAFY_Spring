package com.ssafy.tigetting.admin.controller;

import com.ssafy.tigetting.admin.dto.ApiResponse;
import com.ssafy.tigetting.admin.service.AdminUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "Admin User", description = "관리자 유저 관리 API")
@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final AdminUserService adminUserService;

    /**
     * 유저 목록 조회 (페이징, 검색, 필터링)
     */
    @Operation(summary = "유저 목록 조회", description = "페이징, 검색, 필터링이 가능한 유저 목록 조회")
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserList(
            @Parameter(description = "페이지 번호 (0부터 시작)") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "페이지 크기") @RequestParam(defaultValue = "20") int size,
            @Parameter(description = "검색어 (이름 또는 이메일)") @RequestParam(required = false) String search,
            @Parameter(description = "역할 필터 (USER, ADMIN)") @RequestParam(required = false) String role) {
        Map<String, Object> result = adminUserService.getUserList(page, size, search, role);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 유저 상세 조회
     */
    @Operation(summary = "유저 상세 조회", description = "유저 ID로 상세 정보 조회")
    @GetMapping("/{userid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUserDetail(
            @Parameter(description = "유저 ID") @PathVariable int userid) {
        Map<String, Object> result = adminUserService.getUserDetail(userid);
        return ResponseEntity.ok(ApiResponse.success(result));
    }

    /**
     * 유저 수정
     */
    @Operation(summary = "유저 수정", description = "유저 정보 수정")
    @PutMapping("/{userid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> updateUser(
            @Parameter(description = "유저 ID") @PathVariable int userid,
            @RequestBody Map<String, Object> userData) {
        adminUserService.updateUser(userid, userData);
        return ResponseEntity.ok(ApiResponse.success(null));
    }

    /**
     * 유저 삭제
     */
    @Operation(summary = "유저 삭제", description = "유저 삭제")
    @DeleteMapping("/{userid}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Void>> deleteUser(
            @Parameter(description = "유저 ID") @PathVariable int userid) {
        adminUserService.deleteUser(userid);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
