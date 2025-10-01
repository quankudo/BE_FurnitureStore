package com.furniture.store.mapper;

import com.furniture.store.dto.request.SupplierRequest;
import com.furniture.store.dto.response.SupplierResponse;
import com.furniture.store.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {
    public Supplier toEntity(SupplierRequest request) {
        return Supplier.builder()
                .description(request.getDescription())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .supplierName(request.getSupplierName())
                .contactName(request.getContactName())
                .website(request.getWebsite())
                .build();
    }

    public void updateEntity(SupplierRequest request, Supplier supplier){
        supplier.setSupplierName(request.getSupplierName());
        supplier.setAddress(request.getAddress());
        supplier.setEmail(request.getEmail());
        supplier.setPhone(request.getPhone());
        supplier.setContactName(request.getContactName());
        supplier.setWebsite(request.getWebsite());
        supplier.setDescription(request.getDescription());
    }

    public SupplierResponse toResponse(Supplier supplier) {
        return SupplierResponse.builder()
                .id(supplier.getId())
                .description(supplier.getDescription())
                .email(supplier.getEmail())
                .phone(supplier.getPhone())
                .address(supplier.getAddress())
                .supplierName(supplier.getSupplierName())
                .contactName(supplier.getContactName())
                .website(supplier.getWebsite())
                .build();
    }
}
