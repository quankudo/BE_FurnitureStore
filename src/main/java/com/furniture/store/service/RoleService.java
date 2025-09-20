package com.furniture.store.service;

import com.furniture.store.dto.request.RoleRequest;
import com.furniture.store.dto.response.RoleResponse;
import com.furniture.store.entity.Role;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.RoleMapper;
import com.furniture.store.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    public RoleResponse create(RoleRequest request) {
        if(roleRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.ROLE_EXISTED);
        Role roleEntity = roleMapper.toEntity(request);
        roleEntity = roleRepository.save(roleEntity);
        return roleMapper.toResponse(roleEntity);
    }

    public List<RoleResponse> getAll(){
        return roleRepository.findAll()
                .stream().map(roleMapper::toResponse)
                .toList();
    }

    public void deleteRole(String role) {
        Role existsRole = roleRepository.findByName(role)
                .orElseThrow(() -> new AppException(ErrorCode.ROLE_NOT_EXISTED));
        roleRepository.delete(existsRole);
    }
}
