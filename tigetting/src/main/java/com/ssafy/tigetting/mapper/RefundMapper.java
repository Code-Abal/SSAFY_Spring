package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.Refund;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface RefundMapper {
    void save(Refund refund);

    void update(Refund refund);

    Optional<Refund> findById(Long id);

    List<Refund> findByBookingId(Long bookingId);

    List<Refund> findByPaymentId(Long paymentId);
}
