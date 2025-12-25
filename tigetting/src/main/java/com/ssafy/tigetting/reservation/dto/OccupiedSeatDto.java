package com.ssafy.tigetting.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OccupiedSeatDto {
    private String section;
    private String row;
    private String number;
}
