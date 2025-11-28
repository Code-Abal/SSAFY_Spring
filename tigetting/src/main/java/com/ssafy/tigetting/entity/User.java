package com.ssafy.tigetting.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    private Long userId;

    private String username;

    private String email;

    private String passwordHash;

    private String phoneNumber;

    private Role role;

    private LocalDateTime lastLogin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private List<Booking> bookings;

    private List<SeatLock> seatLocks;

    public enum Role {
        USER, ADMIN
    }
}