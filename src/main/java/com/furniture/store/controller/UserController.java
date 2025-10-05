package com.furniture.store.controller;

import com.furniture.store.constant.PredefinedPermission;
import com.furniture.store.constant.PredefinedRole;
import com.furniture.store.dto.request.ChangePasswordRequest;
import com.furniture.store.dto.request.UserCreationRequest;
import com.furniture.store.dto.request.UserUpdateInfoRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.dto.response.UserResponse;
import com.furniture.store.dto.response.UserUpdateInfoResponse;
import com.furniture.store.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping
    public ApiResponse<UserResponse> createAccount(
            @RequestBody UserCreationRequest request,
            @RequestParam(defaultValue = PredefinedRole.USER_ROLE) String role) {
        if(role.equals(PredefinedRole.USER_ROLE))
            return ApiResponse.<UserResponse>builder()
                    .result(userService.createUser(request))
                    .build();
        return ApiResponse.<UserResponse>builder()
                .result(userService.createAccountForEmployee(request))
                .build();
    }

    @GetMapping("/my-info")
    ApiResponse<UserResponse> getMyInfo() {
        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

    @GetMapping
    public ApiResponse<PaginationResponse<UserResponse>> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = PredefinedRole.USER_ROLE) String role,
            @RequestParam(required = false, defaultValue = "") String permission
    ) {
        PaginationResponse<UserResponse> users = userService.getAll(page, size, keyword, role, permission);
        return ApiResponse.<PaginationResponse<UserResponse>>builder()
                .result(users)
                .build();
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping
    ApiResponse<UserUpdateInfoResponse> updateUser(@RequestBody UserUpdateInfoRequest request) {
        return ApiResponse.<UserUpdateInfoResponse>builder()
                .result(userService.updateInformation(request))
                .build();
    }

    @PutMapping("/change-password")
    ApiResponse<Void> changePassword(@RequestBody ChangePasswordRequest request) {
        userService.changePassword(request);
        return ApiResponse.<Void>builder().build();
    }
}
