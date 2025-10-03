package com.furniture.store.service;

import com.furniture.store.dto.request.BlogCategoryRequest;
import com.furniture.store.dto.response.BlogCategoryResponse;
import com.furniture.store.entity.BlogCategory;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.BlogCategoryMapper;
import com.furniture.store.repository.BlogCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogCategoryService {
    BlogCategoryRepository blogCategoryRepository;
    BlogCategoryMapper blogCategoryMapper;

    public BlogCategoryResponse create(BlogCategoryRequest request) {
        if(blogCategoryRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.BLOG_CATEGORY_ALREADY_EXISTS);
        }

        BlogCategory blogCategory = blogCategoryMapper.toEntity(request);
        return blogCategoryMapper.toResponse(blogCategoryRepository.save(blogCategory));
    }

    public BlogCategoryResponse update(BlogCategoryRequest request, Long id) {
        BlogCategory blogCategory = blogCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_CATEGORY_NOT_FOUND));

        // Chỉ check trùng name nếu có thay đổi
        if (!Objects.equals(blogCategory.getName(), request.getName()) &&
                blogCategoryRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.BLOG_CATEGORY_ALREADY_EXISTS);
        }

        blogCategoryMapper.update(blogCategory, request);
        return blogCategoryMapper.toResponse(blogCategoryRepository.save(blogCategory));
    }

    public void delete(Long id){
        BlogCategory blogCategory = blogCategoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_CATEGORY_NOT_FOUND));
        blogCategoryRepository.delete(blogCategory);
    }

    public List<BlogCategoryResponse> getAll(){
        return blogCategoryRepository.findAll().stream().map(blogCategoryMapper::toResponse).toList();
    }
}
