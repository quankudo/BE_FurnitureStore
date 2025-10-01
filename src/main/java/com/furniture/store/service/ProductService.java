package com.furniture.store.service;

import com.furniture.store.dto.request.ProductAttributeRequest;
import com.furniture.store.dto.request.ProductInfoRequest;
import com.furniture.store.dto.request.ProductRequest;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.dto.response.ProductAttributeResponse;
import com.furniture.store.dto.response.ProductInfoResponse;
import com.furniture.store.dto.response.ProductResponse;
import com.furniture.store.entity.*;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.*;
import com.furniture.store.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Builder.Default cho list trong entity để tránh NullPointerException.
//Helper method cho orElseThrow.
//@Transactional ở service.
//Cân nhắc xử lý fetch join để tránh N+1 query.

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;
    ProductAttributeRepository productAttributeRepository;
    ProductMapper productMapper;

    CategoryRepository categoryRepository;
    SupplierRepository supplierRepository;
    ColorRepository colorRepository;
    MaterialRepository materialRepository;

    public ProductResponse create(ProductRequest request) {
        Product product = productMapper.toProductEntity(request.getProductInfoRequest());

        product.setCategory(categoryRepository.findById(request.getProductInfoRequest().getCategoryId())
                .orElseThrow(()->new AppException(ErrorCode.CATEGORY_NOT_FOUND)));
        product.setSupplier(supplierRepository.findById(request.getProductInfoRequest().getSupplierId())
                .orElseThrow(()->new AppException(ErrorCode.SUPPLIER_NOT_FOUND)));

        if (request.getAttributes() != null) {
            request.getAttributes().forEach(attr ->
                    product.getAttributes().add(productMapper.toProductAttribute(attr, product))
            );
        }

        if (request.getVariants() != null) {
            request.getVariants().forEach(variantReq -> {
                ProductVariant variant = productMapper.toProductVariant(variantReq, product);

                variant.setMaterial(materialRepository.findById(variantReq.getMaterialId())
                        .orElseThrow(()->new AppException(ErrorCode.MATERIAL_NOT_FOUND)));
                variant.setColor(colorRepository.findById(variantReq.getColorId())
                        .orElseThrow(()->new AppException(ErrorCode.COLOR_NOT_FOUND)));
                product.getVariants().add(variant);
            });
        }
        Product savedProduct = productRepository.save(product);
        return productMapper.toResponse(savedProduct);
    }

    //Nên thêm trường isActive chứ không xóa hẳn nha! oh lala
    public void delete(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        productRepository.delete(product);
    }

    public ProductResponse getById(String id) {
        Product product = productRepository.findByIdWithDetails(id)
                .orElseThrow(()-> new AppException(ErrorCode.PRODUCT_NOT_FOUND));
        return productMapper.toResponse(product);
    }

    public PaginationResponse<ProductInfoResponse> getListPreView(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Product> products = productRepository.findAll(pageable);
        return new PaginationResponse<ProductInfoResponse>(
                products.getContent().stream().map(productMapper::toProductInfoResponse).toList(),
                products.getNumber(),
                products.getTotalPages(),
                products.getTotalElements()
        );
    }

    //Update Attribute
    //Nhận lại: List Id Attribute bị xóa | List Attribute sẽ cập nhật
    @Transactional
    public List<ProductAttributeResponse> updateAttribute(
            List<ProductAttributeRequest> listUpdate,
            String productId) {

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        product.getAttributes().clear();

        if (listUpdate != null) {
            for (ProductAttributeRequest req : listUpdate) {
                product.getAttributes().add(productMapper.toProductAttribute(req, product));
            }
        }

        Product saved = productRepository.save(product);

        return saved.getAttributes().stream()
                .map(productMapper::toProductAttributeResponse)
                .toList();
    }

    //Update Info
    //Nên kiểm tra category và supplier để tránh gọi DB nếu không có thay đổi
    public ProductInfoResponse updateInfo(ProductInfoRequest request, String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        productMapper.toProductEntity(product, request);

        if (!product.getCategory().getId().equals(request.getCategoryId())) {
            Category category = categoryRepository.findById(request.getCategoryId())
                    .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_FOUND));
            product.setCategory(category);
        }

        if (!product.getSupplier().getId().equals(request.getSupplierId())) {
            Supplier supplier = supplierRepository.findById(request.getSupplierId())
                    .orElseThrow(() -> new AppException(ErrorCode.SUPPLIER_NOT_FOUND));
            product.setSupplier(supplier);
        }

        Product saved = productRepository.save(product);

        return productMapper.toProductInfoResponse(saved);
    }

    //Update Variant
    //Nhận lại: List Id Variant bị xóa | List Variant sẽ cập nhật

    //Tích hợp Redis
}
