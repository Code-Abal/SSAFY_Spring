package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.ScheduleSeat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ScheduleSeatMapper {
    void save(ScheduleSeat seat);

    void update(ScheduleSeat seat);

    Optional<ScheduleSeat> findById(Long id);

    List<ScheduleSeat> findByScheduleId(Long scheduleId);

    void deleteById(Long id);
}
