package com.furniture.store.controller;

import com.furniture.store.dto.request.SupplierRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.SupplierResponse;
import com.furniture.store.service.SupplierService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/suppliers")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierController {
    SupplierService supplierService;

    @PostMapping
    public ApiResponse<SupplierResponse> create(@RequestBody SupplierRequest request){
        return ApiResponse.<SupplierResponse>builder()
                .result(supplierService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<SupplierResponse>> getAll() {
        return ApiResponse.<List<SupplierResponse>>builder()
                .result(supplierService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<SupplierResponse> update(@RequestBody SupplierRequest request,
                                                @PathVariable Long id) {
        return ApiResponse.<SupplierResponse>builder()
                .result(supplierService.update(request, id))
                .build();
    }
}
