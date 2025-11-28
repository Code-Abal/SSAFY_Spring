package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.SeatLock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface SeatLockMapper {
    void save(SeatLock seatLock);

    void update(SeatLock seatLock);

    Optional<SeatLock> findById(Long id);

    List<SeatLock> findBySeatId(Long seatId);

    void deleteById(Long id);
}
