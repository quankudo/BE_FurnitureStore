package com.furniture.store.mapper;

import com.furniture.store.dto.response.CityResponse;
import com.furniture.store.entity.City;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    public CityResponse toResponse(City city) {
        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .build();
    }
}
