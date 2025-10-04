package com.furniture.store.controller;

import com.furniture.store.dto.request.CommentRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.CommentResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.service.CommentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentController {
    CommentService commentService;

    @PostMapping
    public ApiResponse<CommentResponse> create(@RequestBody CommentRequest request){
        return ApiResponse.<CommentResponse>builder()
                .result(commentService.create(request))
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ApiResponse.<Void>builder().build();
    }

    @GetMapping("/replies/{id}")
    public ApiResponse<List<CommentResponse>> getRepliesByIdComment(@PathVariable Long id) {
        return ApiResponse.<List<CommentResponse>>builder()
                .result(commentService.getReplyComments(id))
                .build();
    }

    @GetMapping("/{blogId}")
    public ApiResponse<PaginationResponse<CommentResponse>> getComments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @PathVariable Long blogId
    ) {
        return ApiResponse.<PaginationResponse<CommentResponse>>builder()
                .result(commentService.getComments(page, size, blogId))
                .build();
    }
}
