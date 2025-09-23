package com.furniture.store.service;

import com.furniture.store.dto.request.DistrictRequest;
import com.furniture.store.dto.response.DistrictResponse;
import com.furniture.store.entity.City;
import com.furniture.store.entity.District;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.DistrictMapper;
import com.furniture.store.repository.CityRepository;
import com.furniture.store.repository.DistrictRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class DistrictService {
    DistrictRepository districtRepository;
    CityRepository cityRepository;
    DistrictMapper districtMapper;

    public DistrictResponse create(DistrictRequest request) {
        if (districtRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DISTRICT_ALREADY_EXISTS);
        }
        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(()-> new AppException(ErrorCode.CITY_NOT_FOUND));

        District district = new District();
        district.setName(request.getName());
        district.setCity(city);

        District savedDistrict = districtRepository.save(district);

        return districtMapper.toResponse(savedDistrict);
    }

    public List<DistrictResponse> getAll() {
        return districtRepository.findAll().stream().map(districtMapper::toResponse).toList();
    }

    public List<DistrictResponse> getByName(String name) {
        return districtRepository.findByNameContaining(name).stream().map(districtMapper::toResponse).toList();
    }

    public DistrictResponse update(Long id, DistrictRequest request) {
        if (districtRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new AppException(ErrorCode.DISTRICT_ALREADY_EXISTS);
        }

        District district = districtRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.DISTRICT_NOT_FOUND));

        City city = cityRepository.findById(request.getCityId())
                .orElseThrow(()-> new AppException(ErrorCode.CITY_NOT_FOUND));

        district.setName(request.getName());
        district.setCity(city);

        return districtMapper.toResponse(districtRepository.save(district));
    }

    public void delete(Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.DISTRICT_NOT_FOUND));
        districtRepository.delete(district);
    }
}
