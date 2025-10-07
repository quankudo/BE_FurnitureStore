package com.furniture.store.controller;

import com.furniture.store.dto.request.NotificationRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.NotificationResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.service.NotificationService;
import com.furniture.store.service.UserNotificationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notification")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationController {
    NotificationService notificationService;
    UserNotificationService userNotificationService;

    @PostMapping
    public ApiResponse<Void> create(@RequestBody NotificationRequest request) {
        notificationService.create(request);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping
    public ApiResponse<PaginationResponse<NotificationResponse>> getByUser(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ApiResponse.<PaginationResponse<NotificationResponse>>builder()
                .result(notificationService.getByUser(page, size))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<Void> markAsRead(@PathVariable Long id) {
        userNotificationService.markAsRead(id);
        return ApiResponse.<Void>builder().build();
    }
}