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
public class MaterialRequest {
    @NotBlank(message = "MATERIAL_NAME_REQUIRED")
    @Size(max = 100, message = "MATERIAL_NAME_REQUIRED")
    String materialName;

    @Size(max = 255, message = "MATERIAL_DESC_TOO_LONG")
    String description;
}
