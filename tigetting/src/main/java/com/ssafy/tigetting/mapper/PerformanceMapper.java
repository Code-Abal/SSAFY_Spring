package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.Performance;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PerformanceMapper {
    void save(Performance performance);

    void update(Performance performance);

    Optional<Performance> findById(Long id);

    List<Performance> findAll();

    List<Performance> findByVenueId(Long venueId);

    void deleteById(Long id);
}
