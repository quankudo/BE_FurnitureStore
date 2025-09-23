package com.furniture.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_variant_images")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VariantImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "variant_id")
     ProductVariant variant;

    String imageUrl;
}
