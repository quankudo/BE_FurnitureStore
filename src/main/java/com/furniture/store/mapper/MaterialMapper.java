package com.furniture.store.mapper;

import com.furniture.store.dto.request.MaterialRequest;
import com.furniture.store.dto.response.MaterialResponse;
import com.furniture.store.entity.Material;
import org.springframework.stereotype.Component;

@Component
public class MaterialMapper {
    public Material toEntity(MaterialRequest request) {
        return Material.builder()
                .description(request.getDescription())
                .materialName(request.getMaterialName())
                .build();
    }

    public void updateEntity(MaterialRequest materialRequest, Material material) {
        material.setMaterialName(materialRequest.getMaterialName());
        material.setDescription(materialRequest.getDescription());
    }

    public MaterialResponse toResponse(Material material){
        return MaterialResponse.builder()
                .id(material.getId())
                .materialName(material.getMaterialName())
                .description(material.getDescription())
                .build();
    }
}
