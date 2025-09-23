package com.furniture.store.mapper;

import com.furniture.store.dto.request.ColorRequest;
import com.furniture.store.dto.response.ColorResponse;
import com.furniture.store.entity.Color;
import org.springframework.stereotype.Component;

@Component
public class ColorMapper {
    public ColorResponse toResponse(Color color) {
        return ColorResponse.builder()
                .colorName(color.getColorName())
                .id(color.getId())
                .colorValue(color.getColorValue())
                .build();
    }

    public Color toEntity(ColorRequest color) {
        return Color.builder()
                .colorName(color.getColorName())
                .colorValue(color.getColorValue())
                .build();
    }
}
