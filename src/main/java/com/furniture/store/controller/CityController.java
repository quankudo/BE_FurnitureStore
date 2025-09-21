package com.furniture.store.controller;

import com.furniture.store.dto.request.CityRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.CityResponse;
import com.furniture.store.service.CityService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/cities")
public class CityController {
    CityService cityService;

    @PostMapping
    public ApiResponse<CityResponse> create(@RequestBody CityRequest cityRequest) {
        return ApiResponse.<CityResponse>builder()
                .result(cityService.create(cityRequest))
                .build();
    }

    @GetMapping
    public ApiResponse<List<CityResponse>> getCities(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return ApiResponse.<List<CityResponse>>builder()
                    .result(cityService.getByName(name))
                    .build();
        }
        return ApiResponse.<List<CityResponse>>builder()
                .result(cityService.getAll())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<CityResponse> update(@PathVariable Long id,
                                            @RequestBody CityRequest request){
        return ApiResponse.<CityResponse>builder()
                .result(cityService.update(id, request))
                .build();
    }
}
