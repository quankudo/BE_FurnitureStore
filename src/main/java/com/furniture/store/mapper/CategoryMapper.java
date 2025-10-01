package com.furniture.store.mapper;

import com.furniture.store.dto.request.CategoryCreationRequest;
import com.furniture.store.dto.response.CategoryResponse;
import com.furniture.store.entity.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category toEntity(CategoryCreationRequest request) {
        return Category.builder()
                .description(request.getDesc())
                .name(request.getName())
                .build();
    }

    public CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .desc(category.getDescription())
                .slug(category.getSlug())
                .id(category.getId())
                .name(category.getName())
                .isActive(category.isActive())
                .imageUrl(category.getImageUrl())
                .imagePublicId(category.getImagePublicId())
                .build();
    }
}
