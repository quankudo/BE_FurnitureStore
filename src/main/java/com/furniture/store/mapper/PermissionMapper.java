package com.furniture.store.mapper;

import com.furniture.store.dto.request.PermissionRequest;
import com.furniture.store.dto.response.PermissionResponse;
import com.furniture.store.entity.Permission;
import org.springframework.stereotype.Component;

@Component
public class PermissionMapper {
    public Permission toEntity(PermissionRequest request){
        Permission permission = new Permission();
        permission.setName(request.getName());
        permission.setDescription(request.getDescription());
        return permission;
    }

    public PermissionResponse toResponse(Permission permission){
        PermissionResponse permissionResponse = new PermissionResponse();
        permissionResponse.setId(permission.getId());
        permissionResponse.setName(permission.getName());
        permissionResponse.setDescription(permission.getDescription());
        return permissionResponse;
    }
}
