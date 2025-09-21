package com.furniture.store.service;

import com.furniture.store.dto.request.CategoryCreationRequest;
import com.furniture.store.dto.response.CategoryResponse;
import com.furniture.store.entity.Category;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.CategoryMapper;
import com.furniture.store.repository.CategoryRepository;
import com.furniture.store.utils.SlugUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryService {

    CategoryRepository categoryRepository;
    CategoryMapper categoryMapper;
    CloudinaryService cloudinaryService;

    public CategoryResponse create(CategoryCreationRequest request, MultipartFile image){
        String slug = SlugUtils.generateSlug(request.getName());
        if (categoryRepository.existsByNameAndSlug(request.getName(), slug)) {
            throw new AppException(ErrorCode.CATEGORY_ALREADY_EXISTS);
        }
        Category categoryEntity = categoryMapper.toEntity(request);
        categoryEntity.setActive(true);
        categoryEntity.setSlug(slug);

        if (image != null && !image.isEmpty()) {
            String imageUrl = cloudinaryService.uploadFile(image, "categories");
            categoryEntity.setImageUrl(imageUrl);
        }

        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    public List<CategoryResponse> getAll(){
        return categoryRepository.findAll().stream().map(categoryMapper::toResponse).toList();
    }

    public List<CategoryResponse> getByName(String name){
        return categoryRepository.findByNameContaining(name).stream().map(categoryMapper::toResponse).toList();
    }

    public CategoryResponse toggleActivateCategory(String categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));

        category.setActive(!category.isActive());
        Category saved = categoryRepository.save(category);

        return categoryMapper.toResponse(saved);
    }
}
