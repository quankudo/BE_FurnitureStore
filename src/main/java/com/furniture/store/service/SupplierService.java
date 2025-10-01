package com.furniture.store.service;

import com.furniture.store.dto.request.SupplierRequest;
import com.furniture.store.dto.response.SupplierResponse;
import com.furniture.store.entity.Supplier;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.SupplierMapper;
import com.furniture.store.repository.SupplierRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SupplierService {
    SupplierRepository supplierRepository;
    SupplierMapper supplierMapper;

    public SupplierResponse create(SupplierRequest request){
        if(supplierRepository.existsBySupplierName(request.getSupplierName())){
            throw new AppException(ErrorCode.SUPPLIER_ALREADY_EXISTS);
        }

        Supplier supplier = supplierMapper.toEntity(request);
        return supplierMapper.toResponse(supplierRepository.save(supplier));
    }

    public List<SupplierResponse> getAll(){
        return supplierRepository.findAll().stream().map(supplierMapper::toResponse).toList();
    }

    public SupplierResponse update(SupplierRequest request, Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(()->new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
        supplierMapper.updateEntity(request, supplier);

        return supplierMapper.toResponse(supplierRepository.save(supplier));
    }
}
