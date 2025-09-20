package com.furniture.store.mapper;

import com.furniture.store.dto.request.RoleRequest;
import com.furniture.store.dto.response.RoleResponse;
import com.furniture.store.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {
    public Role toEntity(RoleRequest request){
        Role roleEntity = new Role();
        roleEntity.setName(request.getName());
        roleEntity.setDescription(request.getDescription());
        return roleEntity;
    }

    public RoleResponse toResponse(Role role){
        RoleResponse roleResponse = new RoleResponse();
        roleResponse.setId(role.getId());
        roleResponse.setName(role.getName());
        roleResponse.setDescription(role.getDescription());
        return roleResponse;
    }
}
