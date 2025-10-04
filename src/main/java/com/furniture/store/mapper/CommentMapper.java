package com.furniture.store.mapper;

import com.furniture.store.dto.request.CommentRequest;
import com.furniture.store.dto.response.CommentResponse;
import com.furniture.store.entity.Comment;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentMapper {

    UserMapper userMapper;

    public Comment toEntity(CommentRequest request) {
        return Comment.builder()
                .content(request.getContent())
                .build();
    }

    public CommentResponse toResponse(Comment comment) {
        return toResponse(comment, 0, 0);
    }

    public CommentResponse toResponse(Comment comment, int currentDepth, int maxDepth) {
        List<CommentResponse> replies = Collections.emptyList();
        if(currentDepth < maxDepth && comment.getReplies() != null) {
            replies = comment.getReplies().stream()
                    .map(c -> toResponse(c, currentDepth + 1, maxDepth))
                    .collect(Collectors.toList());
        }

        return CommentResponse.builder()
                .id(comment.getId())
                .authorName(userMapper.toPreViewInfo(comment.getUser()))
                .blogId(comment.getBlog() != null ? comment.getBlog().getId() : null)
                .content(comment.isDeleted() ? "Comment đã bị xóa" : comment.getContent())
                .replies(replies)
                .createdAt(comment.getCreatedAt())
                .parentId(comment.getParent() != null ? comment.getParent().getId() : null)
                .build();
    }
}
