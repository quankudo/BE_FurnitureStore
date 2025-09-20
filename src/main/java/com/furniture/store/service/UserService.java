package com.furniture.store.service;

import com.furniture.store.constant.PredefinedRole;
import com.furniture.store.dto.request.UserCreationRequest;
import com.furniture.store.dto.request.UserUpdateInfoRequest;
import com.furniture.store.dto.response.UserResponse;
import com.furniture.store.dto.response.UserUpdateInfoResponse;
import com.furniture.store.entity.Role;
import com.furniture.store.entity.User;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.UserMapper;
import com.furniture.store.repository.PermissionRepository;
import com.furniture.store.repository.RoleRepository;
import com.furniture.store.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserService {
    UserRepository userRepository;
    PermissionRepository permissionRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;
    MailService mailService;

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String email = context.getAuthentication().getName();

        User user = userRepository.findByEmail(email).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toResponse(user);
    }

    private User createAccount(UserCreationRequest user, String roleName) {
        if(userRepository.existsByEmail(user.getEmail())){
            throw new RuntimeException("Da ton tai tai khoan voi email " + user.getEmail());
        }

        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));

        User userEntity = userMapper.toEntity(user);
        userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
        userEntity.setRoles(Set.of(role));
        userEntity.setTokenActive(UUID.randomUUID().toString());

        return userRepository.save(userEntity);
    }

    public UserResponse createUser(UserCreationRequest user) {
        User newUser = createAccount(user, PredefinedRole.USER_ROLE);
        mailService.sendActivationEmail(newUser.getEmail(), newUser.getTokenActive());
        return userMapper.toResponse(newUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public UserResponse createAccountForEmployee(UserCreationRequest user) {
        User newUser = createAccount(user, PredefinedRole.EMPLOYEE_ROLE);
        return userMapper.toResponse(newUser);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public void delete(String id) {
        User existsUser = userRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));

        userRepository.delete(existsUser);
    }

    public UserUpdateInfoResponse updateInformation(UserUpdateInfoRequest userUpdate) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User existsUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.toUserEntityUpdate(existsUser, userUpdate);
        return userMapper.toUserUpdateInfoRes(userRepository.save(existsUser));
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getAll() {
        return userRepository.findAll().stream().map(userMapper::toResponse).toList();
    }
}
