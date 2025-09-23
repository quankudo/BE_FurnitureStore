package com.furniture.store.mapper;

import com.furniture.store.dto.request.AddressRequest;
import com.furniture.store.dto.response.AddressResponse;
import com.furniture.store.dto.response.CityResponse;
import com.furniture.store.dto.response.DistrictResponse;
import com.furniture.store.entity.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {
    public Address toEntity(AddressRequest request) {
        return Address.builder()
                .addressName(request.getAddressName())
                .phoneNumber(request.getPhoneNumber())
                .detail(request.getDetail())
                .build();
    }

    public void toEntity(AddressRequest request, Address address) {
        address.setAddressName(address.getAddressName());
        address.setDetail(address.getDetail());
        address.setPhoneNumber(address.getPhoneNumber());
    }

    public AddressResponse toResponse(Address address) {
        return AddressResponse.builder()
                .addressName(address.getAddressName())
                .phoneNumber(address.getPhoneNumber())
                .detail(address.getDetail())
                .isDefault(address.isDefault())
                .city(new CityResponse(address.getCity().getId(), address.getCity().getName()))
                .district(new DistrictResponse(address.getDistrict().getId(), address.getDistrict().getName()))
                .build();
    }
}
