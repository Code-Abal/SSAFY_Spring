package com.ssafy.tigetting.mapper;

import com.ssafy.tigetting.entity.VenueSeat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface VenueSeatMapper {
    void save(VenueSeat seat);

    void update(VenueSeat seat);

    Optional<VenueSeat> findById(Long id);

    List<VenueSeat> findByVenueId(Long venueId);

    void deleteById(Long id);
}
