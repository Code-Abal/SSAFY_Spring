package com.ssafy.tigetting.admin.service;

import com.ssafy.tigetting.mapper.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminUserService {

    private final AdminUserMapper adminUserMapper;

    /**
     * 유저 목록 조회 (페이징, 검색, 필터링)
     */
    public Map<String, Object> getUserList(int page, int size, String search, String role) {
        int offset = page * size;

        // 역할 필터 처리 (문자열을 roleid로 변환)
        // DB: 1=ADMIN, 2=USER, 3=BUSINESS
        Integer roleid = null;
        if (role != null && !role.isEmpty()) {
            if ("USER".equalsIgnoreCase(role)) {
                roleid = 2;
            } else if ("ADMIN".equalsIgnoreCase(role)) {
                roleid = 1;
            } else if ("BUSINESS".equalsIgnoreCase(role)) {
                roleid = 3;
            }
        }

        List<Map<String, Object>> users = adminUserMapper.getUserList(offset, size, search, roleid);
        int totalCount = adminUserMapper.getUserCount(search, roleid);

        Map<String, Object> result = new HashMap<>();
        result.put("users", users);
        result.put("totalCount", totalCount);
        result.put("currentPage", page);
        result.put("pageSize", size);

        return result;
    }

    /**
     * 유저 상세 조회
     */
    public Map<String, Object> getUserDetail(int userid) {
        Map<String, Object> user = adminUserMapper.getUserById(userid);
        if (user == null) {
            throw new RuntimeException("유저를 찾을 수 없습니다: " + userid);
        }

        return user;
    }

    /**
     * 유저 수정
     */
    @Transactional
    public void updateUser(int userid, Map<String, Object> userData) {
        Map<String, Object> existing = adminUserMapper.getUserById(userid);
        if (existing == null) {
            throw new RuntimeException("유저를 찾을 수 없습니다: " + userid);
        }

        adminUserMapper.updateUser(userid, userData);
    }

    /**
     * 유저 삭제
     */
    @Transactional
    public void deleteUser(int userid) {
        Map<String, Object> existing = adminUserMapper.getUserById(userid);
        if (existing == null) {
            throw new RuntimeException("유저를 찾을 수 없습니다: " + userid);
        }

        adminUserMapper.deleteUser(userid);
    }
}
