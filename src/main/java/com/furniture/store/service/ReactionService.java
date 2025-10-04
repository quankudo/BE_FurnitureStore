package com.furniture.store.service;

import com.furniture.store.dto.request.ReactionRequest;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.dto.response.ReactionResponse;
import com.furniture.store.dto.response.ReactionSummaryResponse;
import com.furniture.store.entity.Blog;
import com.furniture.store.entity.Reaction;
import com.furniture.store.enums.ReactionType;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.ReactionMapper;
import com.furniture.store.repository.BlogRepository;
import com.furniture.store.repository.ReactionRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionService {
    ReactionRepository reactionRepository;
    ReactionMapper reactionMapper;
    BlogRepository blogRepository;
    UserContextService userContextService;

    public ReactionResponse create(ReactionRequest request) {
        var user = userContextService.getCurrentUser();

        // Kiểm tra xem user đã reaction blog này chưa
        Reaction existing = reactionRepository
                .findByUserIdAndBlogId(user.getId(), request.getIdBlog())
                .orElse(null);

        if (existing != null) {
            if (existing.getType().equals(request.getType())) {
                reactionRepository.delete(existing);
                return null;
            }

            // Nếu khác loại -> cập nhật loại mới
            existing.setType(request.getType());
            return reactionMapper.toResponse(reactionRepository.save(existing));
        }

        // Nếu chưa có reaction -> tạo mới
        Blog blog = blogRepository.findById(request.getIdBlog())
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        Reaction reaction = reactionMapper.toEntity(request);
        reaction.setBlog(blog);
        reaction.setUser(user);

        return reactionMapper.toResponse(reactionRepository.save(reaction));
    }

    public PaginationResponse<ReactionResponse> getReactionsByIdBlog(int page, int size, Long idBlog) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Reaction> blogs = reactionRepository.findByBlogId(pageable, idBlog);
        return new PaginationResponse<>(
                blogs.getContent().stream().map(reactionMapper::toResponse).toList(),
                blogs.getNumber(),
                blogs.getTotalPages(),
                blogs.getTotalElements()
        );
    }

    public Map<ReactionType, Long> getReactionSummaryByBlog(Long blogId) {
        if (!blogRepository.existsById(blogId)) {
            throw new AppException(ErrorCode.BLOG_NOT_FOUND);
        }

        Map<ReactionType, Long> summary = reactionRepository.findReactionSummaryByBlogId(blogId)
                .stream()
                .collect(Collectors.toMap(
                        ReactionSummaryResponse::getType,
                        ReactionSummaryResponse::getCount
                ));

        for (ReactionType type : ReactionType.values()) {
            summary.putIfAbsent(type, 0L);
        }

        return summary;
    }
}
