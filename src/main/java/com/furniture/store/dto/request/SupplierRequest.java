package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupplierRequest {
    String supplierName;
    String contactName;
    String phone;
    String email;
    String address;
    String website;
    String description;
}
