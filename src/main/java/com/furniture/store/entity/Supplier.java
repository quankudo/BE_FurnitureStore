package com.furniture.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "tbl_suppliers")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String supplierName;     // Tên nhà cung cấp
    String contactName;      // Người liên hệ
    String phone;            // SĐT
    String email;            // Email
    String address;          // Địa chỉ
    String website;          // Website (nếu có)
    String description;      // Thông tin thêm
}
