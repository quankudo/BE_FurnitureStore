package com.furniture.store.controller;

import com.furniture.store.dto.request.ProductRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.dto.response.ProductInfoResponse;
import com.furniture.store.dto.response.ProductResponse;
import com.furniture.store.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @PostMapping
    public ApiResponse<ProductResponse> createProduct(
            @RequestBody ProductRequest request) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.create(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductResponse> getDetailById(@PathVariable String id) {
        return ApiResponse.<ProductResponse>builder()
                .result(productService.getById(id))
                .build();
    }

    @GetMapping
    public ApiResponse<PaginationResponse<ProductInfoResponse>> getListPreView(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.<PaginationResponse<ProductInfoResponse>>builder()
                .result(productService.getListPreView(page, size))
                .build();
    }


}
