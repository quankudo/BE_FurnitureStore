package com.furniture.store.controller;

import com.furniture.store.dto.request.BlogCategoryRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.BlogCategoryResponse;
import com.furniture.store.service.BlogCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blog-category")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogCategoryController {
    BlogCategoryService blogCategoryService;

    @PostMapping
    public ApiResponse<BlogCategoryResponse> create(@RequestBody BlogCategoryRequest request) {
        return ApiResponse.<BlogCategoryResponse>builder()
                .result(blogCategoryService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<BlogCategoryResponse>> getAll(){
        return ApiResponse.<List<BlogCategoryResponse>>builder()
                .result(blogCategoryService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BlogCategoryResponse> update(@RequestBody BlogCategoryRequest request,
                                                    @PathVariable Long id){
        return ApiResponse.<BlogCategoryResponse>builder()
                .result(blogCategoryService.update(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id){
        blogCategoryService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}
