package com.furniture.store.service;

import com.furniture.store.dto.request.AddressRequest;
import com.furniture.store.dto.response.AddressResponse;
import com.furniture.store.entity.Address;
import com.furniture.store.entity.City;
import com.furniture.store.entity.District;
import com.furniture.store.entity.User;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.AddressMapper;
import com.furniture.store.repository.AddressRepository;
import com.furniture.store.repository.CityRepository;
import com.furniture.store.repository.DistrictRepository;
import com.furniture.store.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AddressService {
    AddressRepository addressRepository;
    CityRepository cityRepository;
    DistrictRepository districtRepository;
    UserRepository userRepository;
    AddressMapper addressMapper;
    UserContextService userContextService;

    public AddressResponse create(AddressRequest request) {
        Address address = addressMapper.toEntity(request);
        User existsUser = userContextService.getCurrentUser();

        if (addressRepository.existsAddress(
                existsUser.getId(),
                request.getDetail(),
                request.getCityId(),
                request.getDistrictId(),
                request.getPhoneNumber(),
                request.getAddressName()).isPresent()) {
            throw new AppException(ErrorCode.ADDRESS_ALREADY_EXISTS);
        }

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(()-> new AppException(ErrorCode.CITY_NOT_FOUND));
        District district = districtRepository.findById(request.getDistrictId())
                .orElseThrow(()-> new AppException(ErrorCode.DISTRICT_NOT_FOUND));

        address.setCity(city);
        address.setDistrict(district);
        address.setUser(existsUser);
        address.setDefault(false);

        Address savedAddress = addressRepository.save(address);
        return addressMapper.toResponse(savedAddress);
    }

    public AddressResponse update(Long id, AddressRequest addressRequest) {
        User existsUser = userContextService.getCurrentUser();

        Address address = addressRepository.findByIdAndUser_Id(id, existsUser.getId())
                .orElseThrow(()-> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        if (addressRepository.existsAddress(
                address.getUser().getId(),
                addressRequest.getDetail(),
                addressRequest.getCityId(),
                addressRequest.getDistrictId(),
                addressRequest.getPhoneNumber(),
                addressRequest.getAddressName()).isPresent()) {
            throw new AppException(ErrorCode.ADDRESS_ALREADY_EXISTS);
        }

        City city = cityRepository.findById(addressRequest.getCityId())
                .orElseThrow(()-> new AppException(ErrorCode.CITY_NOT_FOUND));
        District district = districtRepository.findById(addressRequest.getDistrictId())
                .orElseThrow(()-> new AppException(ErrorCode.DISTRICT_NOT_FOUND));

        addressMapper.toEntity(addressRequest, address);
        address.setCity(city);
        address.setDistrict(district);
        return addressMapper.toResponse(addressRepository.save(address));
    }

    public List<AddressResponse> getAll() {
        return addressRepository.findAll().stream().map(addressMapper::toResponse).toList();
    }

    public List<AddressResponse> getByUserId() {
        User existsUser = userContextService.getCurrentUser();

        return addressRepository.findByUser_Id(existsUser.getId()).stream().map(addressMapper::toResponse).toList();
    }

    public void delete(Long id) {
        User existsUser = userContextService.getCurrentUser();
        Address address = addressRepository.findByIdAndUser_Id(id, existsUser.getId())
                .orElseThrow(()-> new AppException(ErrorCode.ADDRESS_NOT_FOUND));
        addressRepository.delete(address);
    }

    @Transactional
    public AddressResponse toggleDefault(Long id) {
        User existsUser = userContextService.getCurrentUser();

        Address address = addressRepository.findByIdAndUser_Id(id, existsUser.getId())
                .orElseThrow(()-> new AppException(ErrorCode.ADDRESS_NOT_FOUND));

        if (address.isDefault()) return addressMapper.toResponse(address);

        addressRepository.findByUserAndIsDefaultTrue(address.getUser())
                .ifPresent(oldDefault -> {
                    oldDefault.setDefault(false);
                    addressRepository.save(oldDefault);
                });

        address.setDefault(true);
        return addressMapper.toResponse(addressRepository.save(address));
    }
}
