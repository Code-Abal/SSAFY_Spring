package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.SystemMetric;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SystemMetricMapper {
    void save(SystemMetric metric);

    List<SystemMetric> findAll();
}
