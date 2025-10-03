package com.furniture.store.mapper;

import com.furniture.store.dto.request.TagRequest;
import com.furniture.store.dto.response.TagResponse;
import com.furniture.store.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {
    public Tag toEntity(TagRequest request) {
        return Tag.builder()
                .name(request.getName())
                .build();
    }

    public TagResponse toResponse(Tag tag) {
        return TagResponse.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public void update(Tag tag, TagRequest request) {
        if(!tag.getName().equals(request.getName())) {
            tag.setName(request.getName());
        }
    }
}
