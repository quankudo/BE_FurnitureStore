package com.furniture.store.mapper;

import com.furniture.store.dto.request.ProductAttributeRequest;
import com.furniture.store.dto.request.ProductInfoRequest;
import com.furniture.store.dto.request.ProductVariantRequest;
import com.furniture.store.dto.response.*;
import com.furniture.store.entity.Product;
import com.furniture.store.entity.ProductAttribute;
import com.furniture.store.entity.ProductVariant;
import com.furniture.store.entity.VariantImage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductMapper {
    ColorMapper colorMapper;
    MaterialMapper materialMapper;
    CategoryMapper categoryMapper;
    SupplierMapper supplierMapper;

    public Product toProductEntity(ProductInfoRequest productInfoRequest) {
        return Product.builder()
                .productName(productInfoRequest.getProductName())
                .description(productInfoRequest.getDescription())
                .slug(productInfoRequest.getSlug())
                .imageUrl(productInfoRequest.getImageUrl())
                .imagePublicId(productInfoRequest.getImagePublicId())
                .build();
    }

    public void toProductEntity(Product product, ProductInfoRequest productInfoRequest) {
        product.setProductName(productInfoRequest.getProductName());
        product.setDescription(productInfoRequest.getDescription());
        product.setSlug(productInfoRequest.getSlug());
        product.setImageUrl(productInfoRequest.getImageUrl());
        product.setImagePublicId(productInfoRequest.getImagePublicId());
    }

    public ProductAttributeResponse toProductAttributeResponse(ProductAttribute productAttribute) {
        return ProductAttributeResponse.builder()
                .id(productAttribute.getAttributeId())
                .value(productAttribute.getValue())
                .name(productAttribute.getName())
                .build();
    }

    public ProductAttribute toProductAttribute(ProductAttributeRequest productAttributeRequest, Product product) {
        return ProductAttribute.builder()
                .name(productAttributeRequest.getName())
                .value(productAttributeRequest.getValue())
                .product(product)
                .build();
    }

    public ProductVariantResponse toProductVariantResponse(ProductVariant productVariant) {
        return ProductVariantResponse.builder()
                .variantId(productVariant.getVariantId())
                .price(productVariant.getPrice())
                .images(productVariant.getImages().stream().map(this::toImageVariantResponse).toList())
                .stockQuantity(productVariant.getStockQuantity())
                .color(colorMapper.toResponse(productVariant.getColor()))
                .material(materialMapper.toResponse(productVariant.getMaterial()))
                .build();
    }

    public VariantImageResponse toImageVariantResponse(VariantImage variantImage) {
        return VariantImageResponse.builder()
                .imageId(variantImage.getImageId())
                .imageUrl(variantImage.getImageUrl())
                .imagePublicId(variantImage.getImagePublicId())
                .build();
    }

    public ProductVariant toProductVariant(ProductVariantRequest productVariantRequest, Product product) {
        ProductVariant variant = ProductVariant.builder()
                .product(product)
                .price(productVariantRequest.getPrice())
                .stockQuantity(productVariantRequest.getStockQuantity())
                .build();

        // Upload ảnh cho variant
        if (productVariantRequest.getImages() != null) {
            productVariantRequest.getImages().forEach(variantImage ->
                    variant.getImages().add(VariantImage.builder()
                            .imageUrl(variantImage.getImageUrl())
                            .imagePublicId(variantImage.getImagePublicId())
                            .variant(variant)
                            .build())
            );
        }
        return variant;
    }

    public ProductInfoResponse toProductInfoResponse(Product savedProduct) {
        return ProductInfoResponse.builder()
                .id(savedProduct.getId())
                .slug(savedProduct.getSlug())
                .productName(savedProduct.getProductName())
                .imageUrl(savedProduct.getImageUrl())
                .imagePublicId(savedProduct.getImagePublicId())
                .description(savedProduct.getDescription())
                .build();
    }

    public ProductResponse toResponse(Product savedProduct) {
        return ProductResponse.builder()
                .productInfo(toProductInfoResponse(savedProduct))
                .category(categoryMapper.toResponse(savedProduct.getCategory()))
                .supplier(supplierMapper.toResponse(savedProduct.getSupplier()))
                .attributes(savedProduct.getAttributes().stream().map(this::toProductAttributeResponse).toList())
                .variants(savedProduct.getVariants().stream().map(this::toProductVariantResponse).toList())
                .build();
    }
}