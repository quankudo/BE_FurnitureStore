package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateInfoRequest {
    String email;
    String firstName;
    String lastName;
    LocalDate dob;
}
