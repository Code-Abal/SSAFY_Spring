package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.PerformanceSchedule;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PerformanceScheduleMapper {
    void save(PerformanceSchedule schedule);

    void update(PerformanceSchedule schedule);

    Optional<PerformanceSchedule> findById(Long id);

    List<PerformanceSchedule> findByPerformanceId(Long performanceId);

    void deleteById(Long id);
}
