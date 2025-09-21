package com.furniture.store.utils;

import java.text.Normalizer;

public class SlugUtils {
    public static String generateSlug(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        String slug = Normalizer.normalize(name, Normalizer.Form.NFD);
        slug = slug.replaceAll("\\p{M}", ""); // xoá dấu
        slug = slug.toLowerCase();
        slug = slug.replaceAll("[^a-z0-9\\s]", "");
        slug = slug.trim().replaceAll("\\s+", "-");

        return slug;
    }

    // Ngăn tạo instance
    private SlugUtils() {}
}

