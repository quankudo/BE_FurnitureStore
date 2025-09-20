package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateInfoResponse {
    String id;
    String email;
    String firstName;
    String lastName;
    LocalDate dob;
    boolean isActive;
}
