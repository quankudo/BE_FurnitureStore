package com.furniture.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CityRequest {
    @NotBlank(message = "CITY_NAME_REQUIRED")
    @Size(max = 100, message = "CITY_NAME_TOO_LONG")
    private String name;
}

