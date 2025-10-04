package com.furniture.store.mapper;

import com.furniture.store.dto.request.UserCreationRequest;
import com.furniture.store.dto.request.UserUpdateInfoRequest;
import com.furniture.store.dto.response.RoleResponse;
import com.furniture.store.dto.response.UserResponse;
import com.furniture.store.dto.response.UserUpdateInfoResponse;
import com.furniture.store.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserMapper {
    public User toEntity(UserCreationRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setDob(userRequest.getDob());
        return user;
    }

    public UserResponse toResponse(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        userResponse.setDob(user.getDob());
        userResponse.setActive(user.isActive());
        userResponse.setRoles(
                user.getRoles().stream()
                        .map(role -> RoleResponse.builder()
                                .id(role.getId())
                                .name(role.getName())
                                .description(role.getDescription())
                                .build()
                        )
                        .collect(Collectors.toSet())
        );
        return userResponse;
    }

    public UserResponse toPreView(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setEmail(user.getEmail());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        return userResponse;
    }

    public UserResponse toPreViewInfo(User user){
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setFirstName(user.getFirstName());
        userResponse.setLastName(user.getLastName());
        return userResponse;
    }

    public void toUserEntityUpdate(User user, UserUpdateInfoRequest request){
        user.setLastName(request.getLastName());
        user.setFirstName(request.getFirstName());
        user.setDob(request.getDob());
        user.setEmail(request.getEmail());
    }

    public UserUpdateInfoResponse toUserUpdateInfoRes(User user){
        UserUpdateInfoResponse userUpdate = new UserUpdateInfoResponse();
        userUpdate.setId(user.getId());
        userUpdate.setDob(user.getDob());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setLastName(user.getLastName());
        userUpdate.setFirstName(user.getFirstName());
        userUpdate.setActive(user.isActive());
        return userUpdate;
    }
}
