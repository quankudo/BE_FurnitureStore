package com.furniture.store.service;

import com.furniture.store.dto.request.DistrictRequest;
import com.furniture.store.dto.response.DistrictResponse;
import com.furniture.store.entity.District;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.DistrictMapper;
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
    DistrictMapper districtMapper;

    public DistrictResponse create(DistrictRequest request) {
        if (districtRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.DISTRICT_ALREADY_EXISTS);
        }

        District district = new District();
        district.setName(request.getName());

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

        district.setName(request.getName());

        return districtMapper.toResponse(districtRepository.save(district));
    }

    public void delete(Long id) {
        District district = districtRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.DISTRICT_NOT_FOUND));
        districtRepository.delete(district);
    }
}
