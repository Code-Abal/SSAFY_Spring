package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.Payment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PaymentMapper {
    void save(Payment payment);

    void update(Payment payment);

    Optional<Payment> findById(Long id);

    Optional<Payment> findByTransactionId(String transactionId);

    List<Payment> findByBookingId(Long bookingId);
}
