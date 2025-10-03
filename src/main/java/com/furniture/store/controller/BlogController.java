package com.furniture.store.controller;

import com.furniture.store.dto.request.BlogRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.BlogResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.service.BlogService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/blogs")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogController {
    BlogService blogService;

    @PostMapping
    public ApiResponse<BlogResponse> create(@RequestBody BlogRequest request) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<BlogResponse> update(@RequestBody BlogRequest request, @PathVariable Long id) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.update(request, id))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<BlogResponse> getById(@PathVariable Long id) {
        return ApiResponse.<BlogResponse>builder()
                .result(blogService.getById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<PaginationResponse<BlogResponse>> getFilterBlogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.<PaginationResponse<BlogResponse>>builder()
                .result(blogService.getFilterBlogs(page, size))
                .build();
    }
}
