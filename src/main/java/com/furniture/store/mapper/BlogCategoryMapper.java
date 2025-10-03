package com.furniture.store.mapper;

import com.furniture.store.dto.request.BlogCategoryRequest;
import com.furniture.store.dto.response.BlogCategoryResponse;
import com.furniture.store.entity.BlogCategory;
import com.furniture.store.utils.SlugUtils;
import org.springframework.stereotype.Component;

@Component
public class BlogCategoryMapper {
    public BlogCategory toEntity(BlogCategoryRequest request) {
        return BlogCategory.builder()
                .name(request.getName())
                .description(request.getDescription())
                .slug(SlugUtils.generateSlug(request.getName()))
                .build();
    }

    public BlogCategoryResponse toResponse(BlogCategory blogCategory){
        return BlogCategoryResponse.builder()
                .id(blogCategory.getId())
                .name(blogCategory.getName())
                .description(blogCategory.getDescription())
                .slug(blogCategory.getSlug())
                .createdAt(blogCategory.getCreatedAt())
                .updatedAt(blogCategory.getUpdatedAt())
                .build();
    }

    public BlogCategoryResponse toPreView(BlogCategory blogCategory){
        return BlogCategoryResponse.builder()
                .id(blogCategory.getId())
                .name(blogCategory.getName())
                .build();
    }

    public void update(BlogCategory blogCategory, BlogCategoryRequest request) {
        if (!blogCategory.getName().equals(request.getName())) {
            blogCategory.setName(request.getName());
            blogCategory.setSlug(SlugUtils.generateSlug(request.getName()));
        }
        if (!request.getDescription().equals(blogCategory.getDescription())) {
            blogCategory.setDescription(request.getDescription());
        }
    }
}
