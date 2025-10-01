package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierResponse {
    Long id;
    String supplierName;
    String contactName;
    String phone;
    String email;
    String address;
    String website;
    String description;
}
