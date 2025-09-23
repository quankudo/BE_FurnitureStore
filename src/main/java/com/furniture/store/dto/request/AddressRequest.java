package com.furniture.store.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressRequest {
    String detail;
    String addressName;
    String phoneNumber;
    Long cityId;
    Long districtId;
}
