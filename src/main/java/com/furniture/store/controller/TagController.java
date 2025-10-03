package com.furniture.store.controller;

import com.furniture.store.dto.request.TagRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.TagResponse;
import com.furniture.store.service.TagService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tags")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagController {
    TagService tagService;

    @PostMapping
    public ApiResponse<TagResponse> create(@RequestBody TagRequest request) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.create(request))
                .build();
    }

    @GetMapping
    public ApiResponse<List<TagResponse>> getAll() {
        return ApiResponse.<List<TagResponse>>builder()
                .result(tagService.getAll())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        tagService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @PutMapping("/{id}")
    public ApiResponse<TagResponse> update(@RequestBody TagRequest request, @PathVariable Long id) {
        return ApiResponse.<TagResponse>builder()
                .result(tagService.update(request, id))
                .build();
    }
}
