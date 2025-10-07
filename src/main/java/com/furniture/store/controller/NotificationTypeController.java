package com.furniture.store.controller;

import com.furniture.store.dto.request.NotificationTypeRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.NotificationTypeResponse;
import com.furniture.store.service.NotificationTypeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notification-type")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationTypeController {
    NotificationTypeService notificationTypeService;

    @PostMapping
    public ApiResponse<NotificationTypeResponse> create(@RequestBody NotificationTypeRequest request) {
        return ApiResponse.<NotificationTypeResponse>builder()
                .result(notificationTypeService.create(request))
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<NotificationTypeResponse> update(@PathVariable Long id,
                                                        @RequestBody NotificationTypeRequest request) {
        return ApiResponse.<NotificationTypeResponse>builder()
                .result(notificationTypeService.update(request, id))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        notificationTypeService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping
    public ApiResponse<List<NotificationTypeResponse>> getAll() {
        return ApiResponse.<List<NotificationTypeResponse>>builder()
                .result(notificationTypeService.getAll())
                .build();
    }
}
