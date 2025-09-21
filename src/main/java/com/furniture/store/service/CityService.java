package com.furniture.store.service;

import com.furniture.store.dto.request.CityRequest;
import com.furniture.store.dto.response.CityResponse;
import com.furniture.store.entity.City;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.CityMapper;
import com.furniture.store.repository.CityRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE, makeFinal = true)
public class CityService {
    CityRepository cityRepository;
    CityMapper cityMapper;

    public CityResponse create(CityRequest request) {
        if (cityRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.CITY_ALREADY_EXISTS);
        }

        City city = new City();
        city.setName(request.getName());

        City savedCity = cityRepository.save(city);

        return cityMapper.toResponse(savedCity);
    }

    public List<CityResponse> getAll() {
        return cityRepository.findAll().stream().map(cityMapper::toResponse).toList();
    }

    public List<CityResponse> getByName(String name) {
        return cityRepository.findByNameContaining(name).stream().map(cityMapper::toResponse).toList();
    }

    public CityResponse update(Long id, CityRequest request) {
        if (cityRepository.existsByNameAndIdNot(request.getName(), id)) {
            throw new AppException(ErrorCode.CITY_ALREADY_EXISTS);
        }

        City city = cityRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.CITY_NOT_FOUND));

        city.setName(request.getName());

        return cityMapper.toResponse(cityRepository.save(city));
    }
}
