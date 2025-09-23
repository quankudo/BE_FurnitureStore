package com.furniture.store.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddressResponse {
    String detail;
    boolean isDefault = false;
    String addressName;
    String phoneNumber;
    CityResponse city;
    DistrictResponse district;
}
