package com.furniture.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "tbl_categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(nullable = false, unique = true, length = 100)
    String name;

    @Column(length = 255)
    String description;

    @Column(nullable = false, unique = true, length = 150)
    String slug;

    // Ảnh
    @Column(nullable = false)
    String imageUrl;

    @Column(nullable = false)
    String imagePublicId;

    @Column(nullable = false)
    boolean isActive = true;
}