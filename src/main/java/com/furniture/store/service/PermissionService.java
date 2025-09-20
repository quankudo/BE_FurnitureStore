package com.furniture.store.service;

import com.furniture.store.dto.request.PermissionRequest;
import com.furniture.store.dto.response.PermissionResponse;
import com.furniture.store.entity.Permission;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.PermissionMapper;
import com.furniture.store.repository.PermissionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PermissionService {
    PermissionRepository permissionRepository;
    PermissionMapper permissionMapper;

    public PermissionResponse create(PermissionRequest request) {
        if(permissionRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.PERMISSION_EXISTED);
        Permission permissionEntity = permissionMapper.toEntity(request);
        permissionEntity = permissionRepository.save(permissionEntity);
        return permissionMapper.toResponse(permissionEntity);
    }

    public List<PermissionResponse> getAll(){
        return permissionRepository.findAll()
                .stream().map(permissionMapper::toResponse)
                .toList();
    }

    public void deletePermission(String permission) {
        Permission existsPermission = permissionRepository.findByName(permission)
                .orElseThrow(()-> new AppException(ErrorCode.PERMISSION_NOT_EXISTED));
        permissionRepository.delete(existsPermission);
    }
}
