package com.furniture.store.mapper;

import com.furniture.store.dto.request.ReactionRequest;
import com.furniture.store.dto.response.ReactionResponse;
import com.furniture.store.entity.Reaction;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ReactionMapper {
    UserMapper userMapper;

    public Reaction toEntity(ReactionRequest request) {
        return Reaction.builder()
                .type(request.getType())
                .build();
    }

    public ReactionResponse toResponse(Reaction reaction) {
        return ReactionResponse.builder()
                .id(reaction.getId())
                .idBlog(reaction.getBlog().getId())
                .user(userMapper.toPreViewInfo(reaction.getUser()))
                .type(reaction.getType())
                .createdAt(reaction.getCreatedAt())
                .build();
    }
}