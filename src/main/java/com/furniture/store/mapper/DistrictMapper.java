package com.furniture.store.mapper;

import com.furniture.store.dto.response.DistrictResponse;
import com.furniture.store.entity.District;
import org.springframework.stereotype.Component;

@Component
public class DistrictMapper {
    public DistrictResponse toResponse(District district) {
        return DistrictResponse.builder()
                .id(district.getId())
                .name(district.getName())
                .build();
    }
}
