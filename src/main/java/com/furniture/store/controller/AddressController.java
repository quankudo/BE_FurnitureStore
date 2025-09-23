package com.furniture.store.controller;

import com.furniture.store.dto.request.AddressRequest;
import com.furniture.store.dto.response.AddressResponse;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.service.AddressService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressController {

    AddressService addressService;

    @PostMapping
    public ApiResponse<AddressResponse> create(@RequestBody AddressRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<AddressResponse> update(@PathVariable Long id, @RequestBody AddressRequest request) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.update(id, request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<AddressResponse>> getAll() {
        return ApiResponse.<List<AddressResponse>>builder()
                .result(addressService.getAll())
                .build();
    }

    @GetMapping("/toggle/{id}")
    public ApiResponse<AddressResponse> toggleDefault(@PathVariable Long id) {
        return ApiResponse.<AddressResponse>builder()
                .result(addressService.toggleDefault(id))
                .build();
    }

    @GetMapping("/my-address")
    public ApiResponse<List<AddressResponse>> getByUserId() {
        return ApiResponse.<List<AddressResponse>>builder()
                .result(addressService.getByUserId())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        addressService.delete(id);
        return ApiResponse.<Void>builder()
                .build();
    }
}
