package com.ssafy.tigetting.global.security;

import com.ssafy.tigetting.global.exception.AuthException;
import com.ssafy.tigetting.global.exception.ErrorCode;

import jakarta.servlet.http.HttpServletRequest;

public final class TokenExtractor {

    public static String extract(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            throw new AuthException(ErrorCode.TOKEN_INVALID);

        }
        return header.substring(7);
    }
}
