package com.furniture.store.controller;

import com.furniture.store.constant.PredefinedRole;
import com.furniture.store.dto.request.UserCreationRequest;
import com.furniture.store.dto.request.UserUpdateInfoRequest;
import com.furniture.store.dto.response.ApiResponse;
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
    ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable String id){
        userService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/{userId}")
    ApiResponse<UserUpdateInfoResponse> updateUser(@RequestBody UserUpdateInfoRequest request) {
        return ApiResponse.<UserUpdateInfoResponse>builder()
                .result(userService.updateInformation(request))
                .build();
    }
}
