package com.furniture.store.controller;

import com.furniture.store.dto.request.AuthenticationRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.AuthenticationResponse;
import com.furniture.store.service.AuthenticationService;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthenticationService authenticationService;

    @PostMapping("/token")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ApiResponse.<AuthenticationResponse>builder()
                .result(authenticationService.authenticate(request))
                .build();
    }

    @GetMapping("/activate")
    public ApiResponse<Void> activate(@RequestParam String token){
        authenticationService.activate(token);
        return ApiResponse.<Void>builder().build();
    }
}
