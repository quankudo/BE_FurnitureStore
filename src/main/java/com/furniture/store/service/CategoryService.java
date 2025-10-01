package com.furniture.store.service;

import com.furniture.store.dto.request.CategoryCreationRequest;
import com.furniture.store.dto.response.CategoryResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.dto.response.UploadResponse;
import com.furniture.store.entity.Category;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.CategoryMapper;
import com.furniture.store.repository.CategoryRepository;
import com.furniture.store.utils.SlugUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

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
            UploadResponse imageUrl = cloudinaryService.uploadFile(image, "categories"+"/" + UUID.randomUUID());
            categoryEntity.setImagePublicId(imageUrl.getPublicId());
            categoryEntity.setImageUrl(imageUrl.getUrl());
        }

        return categoryMapper.toResponse(categoryRepository.save(categoryEntity));
    }

    public PaginationResponse<CategoryResponse> searchCategories(String keyword, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        Page<Category> categories;

        if(keyword != null && !keyword.isBlank()){
            categories = categoryRepository.findByNameContainingIgnoreCase(keyword, pageable);
        }
        else {
            categories = categoryRepository.findAll(pageable);
        }

        return new PaginationResponse<CategoryResponse>(
                categories.getContent().stream().map(categoryMapper::toResponse).toList(),
                categories.getNumber(),
                categories.getTotalPages(),
                categories.getTotalElements()
        );
    }

    public List<CategoryResponse> getAll() {
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
