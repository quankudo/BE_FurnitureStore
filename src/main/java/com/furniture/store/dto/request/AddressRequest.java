package com.furniture.store.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    @NotBlank(message = "ADDRESS_DETAIL_REQUIRED")
    @Size(max = 255, message = "ADDRESS_DETAIL_TOO_LONG")
    String detail;

    @NotBlank(message = "ADDRESS_NAME_REQUIRED")
    @Size(max = 100, message = "ADDRESS_NAME_TOO_LONG")
    String addressName;

    @NotBlank(message = "ADDRESS_PHONE_REQUIRED")
    @Pattern(regexp = "^(0|\\+84)(\\d{9})$", message = "ADDRESS_PHONE_INVALID")
    String phoneNumber;

    Long cityId;
    Long districtId;
}
