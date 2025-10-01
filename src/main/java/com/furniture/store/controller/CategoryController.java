package com.furniture.store.controller;

import com.furniture.store.dto.request.CategoryCreationRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.CategoryResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.service.CategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true )
@RequestMapping("/categories")
public class CategoryController {
    CategoryService categoryService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ApiResponse<CategoryResponse> create(@RequestPart("name") String name,
                                                @RequestPart("desc") String desc,
                                                @RequestPart("image") MultipartFile image){
        CategoryCreationRequest request = new CategoryCreationRequest(name, desc);
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.create(request, image))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CategoryResponse>> getAll(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return ApiResponse.<List<CategoryResponse>>builder()
                    .result(categoryService.getByName(name))
                    .build();
        }
        return ApiResponse.<List<CategoryResponse>>builder()
                .result(categoryService.getAll())
                .build();
    }

    @GetMapping("/search")
    public ApiResponse<PaginationResponse<CategoryResponse>> searchCategories(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.<PaginationResponse<CategoryResponse>>builder()
                .result(categoryService.searchCategories(keyword, page, size))
                .build();
    }

    @PatchMapping("/{id}/toggle-activate")
    public ApiResponse<CategoryResponse> toggleActivateCategory(@PathVariable String id) {
        return ApiResponse.<CategoryResponse>builder()
                .result(categoryService.toggleActivateCategory(id))
                .build();
    }
}
