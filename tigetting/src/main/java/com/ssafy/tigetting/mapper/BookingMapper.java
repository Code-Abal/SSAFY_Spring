package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.Booking;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BookingMapper {
    void save(Booking booking);

    void update(Booking booking);

    Optional<Booking> findById(Long id);

    List<Booking> findAll();

    List<Booking> findByUserId(Long userId);

    void deleteById(Long id);
}
