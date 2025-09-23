package com.furniture.store.service;

import com.furniture.store.dto.request.MaterialRequest;
import com.furniture.store.dto.response.MaterialResponse;
import com.furniture.store.entity.Material;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.MaterialMapper;
import com.furniture.store.repository.MaterialRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialService {
    MaterialRepository materialRepository;
    MaterialMapper materialMapper;

    public MaterialResponse create(MaterialRequest request) {
        if(materialRepository.existsByMaterialName(request.getMaterialName()))
            throw new AppException(ErrorCode.MATERIAL_ALREADY_EXISTS);

        Material material = materialMapper.toEntity(request);
        return materialMapper.toResponse(materialRepository.save(material));
    }

    public List<MaterialResponse> getAll(){
        return materialRepository.findAll().stream().map(materialMapper::toResponse).toList();
    }
}
