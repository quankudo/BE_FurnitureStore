package com.furniture.store.service;

import com.furniture.store.dto.request.CommentRequest;
import com.furniture.store.dto.response.CommentResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.entity.Blog;
import com.furniture.store.entity.Comment;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.CommentMapper;
import com.furniture.store.repository.BlogRepository;
import com.furniture.store.repository.CommentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentService {
    CommentRepository commentRepository;
    BlogRepository blogRepository;
    UserContextService userContextService;
    CommentMapper commentMapper;

    public CommentResponse create(CommentRequest request) {
        Comment comment = commentMapper.toEntity(request);

        Blog blog = blogRepository.findById(request.getBlogId())
                .orElseThrow(()->new AppException(ErrorCode.BLOG_NOT_FOUND));
        comment.setBlog(blog);

        comment.setUser(userContextService.getCurrentUser());
        if(request.getParentId()!=null) {
            Comment commentParent = commentRepository.findById(request.getParentId())
                    .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND));
            comment.setParent(commentParent);
        }

        return commentMapper.toResponse(commentRepository.save(comment), 0, 0);
    }

    public List<CommentResponse> getReplyComments(Long idComment) {
        Comment comment = commentRepository.findById(idComment)
                .orElseThrow(()->new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if(comment.getReplies()==null || comment.getReplies().isEmpty())
            return Collections.emptyList();

        return comment.getReplies().stream().map(c-> commentMapper.toResponse(c, 0, 1)).toList();
    }

    public PaginationResponse<CommentResponse> getComments(int page, int size, Long blogId) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Comment> blogs = commentRepository.findByParentIsNullAndBlogId(pageable, blogId);
        return new PaginationResponse<>(
                blogs.getContent().stream().map(c-> commentMapper.toResponse(c, 0, 0)).toList(),
                blogs.getNumber(),
                blogs.getTotalPages(),
                blogs.getTotalElements()
        );
    }

    public void delete(Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.COMMENT_NOT_FOUND));

        if (!userContextService.getCurrentUser().getId().equals(comment.getUser().getId())) {
            throw new AppException(ErrorCode.UNAUTHORIZED);
        }

        if (comment.getReplies() != null && !comment.getReplies().isEmpty()) {
            comment.setDeleted(true);
            commentRepository.save(comment);
        }
        else {
            commentRepository.delete(comment);
        }
    }

}
