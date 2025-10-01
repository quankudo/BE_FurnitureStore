package com.furniture.store.service;

import com.furniture.store.dto.request.ColorRequest;
import com.furniture.store.dto.response.ColorResponse;
import com.furniture.store.entity.Color;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.ColorMapper;
import com.furniture.store.repository.ColorRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorService {
    ColorRepository colorRepository;
    ColorMapper colorMapper;

    public ColorResponse create(ColorRequest request) {
        if(colorRepository.existsByColorNameAndColorValue(request.getColorName(), request.getColorValue()))
            throw new AppException(ErrorCode.COLOR_ALREADY_EXISTS);

        Color color = colorMapper.toEntity(request);
        return colorMapper.toResponse(colorRepository.save(color));
    }

    public List<ColorResponse> getAll(){
        return colorRepository.findAll().stream().map(colorMapper::toResponse).toList();
    }

    public ColorResponse update(ColorRequest request, Long id){
        Color color = colorRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.COLOR_NOT_FOUND));

        colorMapper.updateEntity(request, color);

        return colorMapper.toResponse(colorRepository.save(color));
    }
}
