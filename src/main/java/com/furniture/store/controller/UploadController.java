package com.furniture.store.controller;

import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.UploadResponse;
import com.furniture.store.service.CloudinaryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/uploads")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UploadController {

    CloudinaryService cloudinaryService;

    @PostMapping("/upload/{folder}")
    public ApiResponse<UploadResponse> uploadImage(@RequestParam("file") MultipartFile file,
                                                   @PathVariable String folder) {
        if (file.isEmpty()) {
            throw new RuntimeException("File is empty");
        }
        String publicId = folder + "/" + UUID.randomUUID();
        UploadResponse response = cloudinaryService.uploadFile(file, publicId);
        return ApiResponse.<UploadResponse>builder()
                .result(response)
                .build();
    }

    @PostMapping("/upload-multiple/{folder}")
    public ApiResponse<Map<String, List<UploadResponse>>> uploadImages(@RequestParam("files") List<MultipartFile> files,
                                                                       @PathVariable String folder) {
        List<UploadResponse> successList = new ArrayList<>();
        List<UploadResponse> errorList = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String publicId = folder + "/" + UUID.randomUUID();
            try {
                UploadResponse response = cloudinaryService.uploadFile(file, publicId);
                successList.add(response);
            } catch (Exception e) {
                errorList.add(UploadResponse.builder()
                        .url(null)
                        .publicId(publicId)
                        .format(null)
                        .size(file.getSize())
                        .build());
            }
        }

        Map<String, List<UploadResponse>> result = new HashMap<>();
        result.put("success", successList);
        result.put("error", errorList);

        return ApiResponse.<Map<String, List<UploadResponse>>>builder()
                .result(result)
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<Boolean> deleteImage(@RequestParam String publicId) {
        boolean deleted = cloudinaryService.deleteFile(publicId);
        return ApiResponse.<Boolean>builder()
                .result(deleted)
                .build();
    }
}
