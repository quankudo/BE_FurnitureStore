package com.furniture.store.controller;

import com.furniture.store.dto.request.ColorRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.ColorResponse;
import com.furniture.store.service.ColorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/colors")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ColorController {
    ColorService colorService;

    @PostMapping
    public ApiResponse<ColorResponse> create(@RequestBody ColorRequest request){
        return ApiResponse.<ColorResponse>builder()
                .result(colorService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<ColorResponse>> getAll() {
        return ApiResponse.<List<ColorResponse>>builder()
                .result(colorService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ColorResponse> update(@RequestBody ColorRequest request,
                                             @PathVariable Long id){
        return ApiResponse.<ColorResponse>builder()
                .result(colorService.update(request, id))
                .build();
    }
}
