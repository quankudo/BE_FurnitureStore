package com.furniture.store.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tbl_blogs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Blog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, length = 255)
    String title;   // Tiêu đề blog

    @Column(nullable = false, unique = true, length = 255)
    String slug;    // URL thân thiện

    @Column(length = 500)
    String summary; // Tóm tắt ngắn

    @Lob
    @Column(columnDefinition = "TEXT")
    String content; // Nội dung đầy đủ

    String thumbnail; // Link ảnh đại diện

    @Builder.Default
    @Column(nullable = false)
    Boolean published = false; // Đã xuất bản hay chưa

    @CreationTimestamp
    LocalDateTime createdAt;

    @UpdateTimestamp
    LocalDateTime updatedAt;

    // Optional: gắn với user (tác giả)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    User author;

    // Optional: gắn với category blog
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    BlogCategory blogCategory;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tbl_blog_tags",
            joinColumns = @JoinColumn(name = "blog_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    Set<Tag> tags = new HashSet<>();
}
