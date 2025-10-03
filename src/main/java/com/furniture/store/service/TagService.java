package com.furniture.store.service;

import com.furniture.store.dto.request.TagRequest;
import com.furniture.store.dto.response.TagResponse;
import com.furniture.store.entity.Tag;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.TagMapper;
import com.furniture.store.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TagService {
    TagRepository tagRepository;
    TagMapper tagMapper;

    public TagResponse create(TagRequest request) {
        if(tagRepository.existsByName(request.getName())){
            throw new AppException(ErrorCode.TAG_ALREADY_EXISTS);
        }

        Tag tag = tagMapper.toEntity(request);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    public TagResponse update(TagRequest request, Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.TAG_NOT_FOUND));

        if(!tag.getName().equals(request.getName()) && tagRepository.existsByName(request.getName())) {
            throw new AppException(ErrorCode.TAG_ALREADY_EXISTS);
        }

        tagMapper.update(tag, request);
        return tagMapper.toResponse(tagRepository.save(tag));
    }

    public List<TagResponse> getAll() {
        return tagRepository.findAll().stream().map(tagMapper::toResponse).toList();
    }

    public void delete(Long id) {
        Tag tag = tagRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.TAG_NOT_FOUND));

        tagRepository.delete(tag);
    }
}
