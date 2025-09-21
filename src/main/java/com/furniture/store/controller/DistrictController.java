package com.furniture.store.controller;

import com.furniture.store.dto.request.DistrictRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.DistrictResponse;
import com.furniture.store.service.DistrictService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/districts")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DistrictController {
    DistrictService districtService;

    @PostMapping
    public ApiResponse<DistrictResponse> create(@RequestBody DistrictRequest districtRequest) {
        return ApiResponse.<DistrictResponse>builder()
                .result(districtService.create(districtRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<DistrictResponse>> getCities(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return ApiResponse.<List<DistrictResponse>>builder()
                    .result(districtService.getByName(name))
                    .build();
        }
        return ApiResponse.<List<DistrictResponse>>builder()
                .result(districtService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<DistrictResponse> update(@PathVariable Long id,
                                            @RequestBody DistrictRequest districtRequest){
        return ApiResponse.<DistrictResponse>builder()
                .result(districtService.update(id, districtRequest))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> update(@PathVariable Long id){
        districtService.delete(id);
        return ApiResponse.<Void>builder().build();
    }
}
