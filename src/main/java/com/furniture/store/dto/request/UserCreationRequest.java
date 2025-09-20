package com.furniture.store.dto.request;


import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserCreationRequest {
    @NotBlank(message = "EMAIL_REQUIRED")
    @Email(message = "INVALID_EMAIL")
    String email;

    @Size(min = 6, max = 50, message = "INVALID_PASSWORD")
    String password;

    @Size(max = 50, message = "FIRSTNAME_TOO_LONG")
    String firstName;

    @Size(max = 50, message = "LASTNAME_TOO_LONG")
    String lastName;

    @Past(message = "DOB_MUST_BE_PAST")
    LocalDate dob;
}