package com.furniture.store.controller;

import com.furniture.store.dto.request.ReactionRequest;
import com.furniture.store.dto.response.ApiResponse;
import com.furniture.store.dto.response.ReactionResponse;
import com.furniture.store.enums.ReactionType;
import com.furniture.store.service.ReactionService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("reactions")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionController {
    ReactionService reactionService;

    @PostMapping
    public ApiResponse<ReactionResponse> create(@RequestBody ReactionRequest request) {
        return ApiResponse.<ReactionResponse>builder()
                .result(reactionService.create(request))
                .build();
    }

    @GetMapping("/summary/{blogId}")
    public ApiResponse<Map<ReactionType, Long>> getSummaryByBlogId(@PathVariable Long blogId) {
        return ApiResponse.<Map<ReactionType, Long>>builder()
                .result(reactionService.getReactionSummaryByBlog(blogId))
                .build();
    }
}
