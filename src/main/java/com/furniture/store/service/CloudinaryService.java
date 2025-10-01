package com.furniture.store.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.furniture.store.dto.response.UploadResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CloudinaryService {

    Cloudinary cloudinary;

    public UploadResponse uploadFile(MultipartFile file, String publicId) {
        try {
            var uploadResult = cloudinary.uploader().upload(file.getBytes(),
                    ObjectUtils.asMap(
                            "resource_type", "auto",
                            "folder", "furniture-store",
                            "public_id", publicId
                    ));
            return UploadResponse.builder()
                    .url(uploadResult.get("secure_url").toString())
                    .publicId(uploadResult.get("public_id").toString())
                    .format(uploadResult.get("format").toString())
                    .size(Long.parseLong(uploadResult.get("bytes").toString()))
                    .build();
        } catch (IOException e) {
            throw new RuntimeException("Upload image failed", e);
        }
    }

    public boolean deleteFile(String publicId) {
        try {
            var result = cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());
            return "ok".equals(result.get("result")); // nếu Cloudinary trả về "ok" thì xoá thành công
        } catch (IOException e) {
            throw new RuntimeException("Delete image failed", e);
        }
    }
}
