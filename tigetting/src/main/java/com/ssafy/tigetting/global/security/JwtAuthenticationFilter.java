package com.ssafy.tigetting.global.security;

import java.io.IOException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private static final String ALREADY_FILTERED_ATTRIBUTE = "com.ssafy.tigetting.global.security.JwtAuthenticationFilter.ALREADY_FILTERED";

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) {
//        // 이미 이 요청에 대해 필터가 처리되었으면 추가 실행을 막음
//        if (request.getAttribute(ALREADY_FILTERED_ATTRIBUTE) != null) {
//            System.out.println("🔒 JwtFilter.shouldNotFilter - already filtered marker present, skipping. URI: " + request.getRequestURI());
//            return true;
//        }
//
//        // 비-REQUEST 디스패처(예: FORWARD, ERROR)인 경우 필터를 건너뜀
//        if (request.getDispatcherType() != DispatcherType.REQUEST) {
//            System.out.println("🔒 JwtFilter.shouldNotFilter - dispatcher type not REQUEST (" + request.getDispatcherType() + "), skipping. URI: " + request.getRequestURI());
//            return true;
//        }
//
//        String uri = request.getRequestURI();
//        String method = request.getMethod();
//
//        // GET, OPTIONS 요청이고 /api/venues/로 시작하면 필터 건너뜀
//        boolean shouldSkip = uri.startsWith("/auth/") ||
//               uri.startsWith("/performances/") ||
//               ((method.equals("GET") || method.equals("OPTIONS")) && uri.startsWith("/api/venues/")) ||
//               uri.equals("/") ||
//               uri.startsWith("/css/") ||
//               uri.startsWith("/js/") ||
//               uri.startsWith("/images/");
//
//        System.out.println("🔒 JwtFilter - URI: " + uri + " | Method: " + method + " | Skip: " + shouldSkip);
//        return shouldSkip;
//    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {

        // 이미 이 필터에서 처리했던 요청인지 확인 (중복 실행 방지)
        if (request.getAttribute(ALREADY_FILTERED_ATTRIBUTE) != null) {
            // 디버그: 두번째 진입일 경우 바로 체인으로 전달
            System.out.println("🔒 JwtFilter - already filtered, skipping second execution. URI: " + request.getRequestURI());
            filterChain.doFilter(request, response);
            return;
        }
        // 마커 설정
        request.setAttribute(ALREADY_FILTERED_ATTRIBUTE, Boolean.TRUE);

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                // 1. 블랙리스트 체크
//                if (tokenBlacklistService.isTokenBlacklisted(token)) {
//                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    response.getWriter().write("{\"error\":\"Token has been invalidated\"}");
//                    return;
//                }

                // 2. 토큰에서 사용자명 추출
                String username = jwtUtil.extractUsername(token);

                // 3. 토큰 유효성 검증
                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                    if (jwtUtil.validateToken(token, username)) {
                        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                                userDetails, null, userDetails.getAuthorities());
                        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                    }
                }
            } catch (Exception e) {
                // 토큰이 유효하지 않은 경우
                SecurityContextHolder.clearContext(); // 다음 필터로 넘김
            }
        }

        filterChain.doFilter(request, response);
    }
}

