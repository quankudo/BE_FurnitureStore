package com.furniture.store.controller;

import com.furniture.store.dto.request.MaterialRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.MaterialResponse;
import com.furniture.store.service.MaterialService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materials")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MaterialController {
    MaterialService materialService;

    @PostMapping
    public ApiResponse<MaterialResponse> create(@RequestBody MaterialRequest request) {
        return ApiResponse.<MaterialResponse>builder()
                .result(materialService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<MaterialResponse>> getAll(){
        return  ApiResponse.<List<MaterialResponse>>builder()
                .result(materialService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<MaterialResponse> update(@RequestBody MaterialRequest request,
                                                @PathVariable Long id) {
        return ApiResponse.<MaterialResponse>builder()
                .result(materialService.update(request, id))
                .build();
    }
}
