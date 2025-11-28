package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.BookingSeat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookingSeatMapper {
    void save(BookingSeat bookingSeat);

    List<BookingSeat> findByBookingId(Long bookingId);

    void deleteByBookingId(Long bookingId);
}
