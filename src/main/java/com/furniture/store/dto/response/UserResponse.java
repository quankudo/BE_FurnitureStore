package com.furniture.store.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    String id;
    String email;
    String firstName;
    String lastName;
    LocalDate dob;
    boolean isActive;
    Set<RoleResponse> roles;
    Set<PermissionResponse> permissionResponses;
}
