package com.ssafy.tigetting.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 관리자 유저 관리 Mapper
 */
@Mapper
public interface AdminUserMapper {

        /**
         * 유저 목록 조회 (페이징, 검색, 필터링)
         */
        List<Map<String, Object>> getUserList(
                        @Param("offset") int offset,
                        @Param("size") int size,
                        @Param("search") String search,
                        @Param("roleid") Integer roleid);

        /**
         * 유저 총 개수 (필터링 적용)
         */
        int getUserCount(
                        @Param("search") String search,
                        @Param("roleid") Integer roleid);

        /**
         * 유저 ID로 조회
         */
        Map<String, Object> getUserById(@Param("userid") int userid);

        /**
         * 유저 정보 수정
         */
        void updateUser(
                        @Param("userid") int userid,
                        @Param("data") Map<String, Object> data);

        /**
         * 유저 삭제
         */
        void deleteUser(@Param("userid") int userid);
}
