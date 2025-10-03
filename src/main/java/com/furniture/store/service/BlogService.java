package com.furniture.store.service;

import com.furniture.store.dto.request.BlogRequest;
import com.furniture.store.dto.response.BlogResponse;
import com.furniture.store.dto.response.PaginationResponse;
import com.furniture.store.entity.Blog;
import com.furniture.store.entity.BlogCategory;
import com.furniture.store.entity.Tag;
import com.furniture.store.exception.AppException;
import com.furniture.store.exception.ErrorCode;
import com.furniture.store.mapper.BlogMapper;
import com.furniture.store.repository.BlogCategoryRepository;
import com.furniture.store.repository.BlogRepository;
import com.furniture.store.repository.TagRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogService {
    BlogRepository blogRepository;
    BlogCategoryRepository blogCategoryRepository;
    TagRepository tagRepository;
    BlogMapper blogMapper;

    UserContextService userContextService;

    public BlogResponse create(BlogRequest request) {
        Blog blog = blogMapper.toEntity(request);

        BlogCategory blogCategory = blogCategoryRepository.findById(request.getIdBlogCategory())
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_CATEGORY_NOT_FOUND));
        blog.setBlogCategory(blogCategory);

        if (request.getListTags() != null && !request.getListTags().isEmpty()) {
            List<Tag> tags = tagRepository.findAllById(request.getListTags());
            if (tags.size() != request.getListTags().size()) {
                throw new AppException(ErrorCode.TAG_NOT_FOUND);
            }
            blog.setTags(new HashSet<>(tags));
        }

        blog.setAuthor(userContextService.getCurrentUser());

        return blogMapper.toResponse(blogRepository.save(blog));
    }

    //Mặc định list tag trong request là list tag sẽ thêm
    public BlogResponse update(BlogRequest request, Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blogMapper.update(blog, request);

        if (!Objects.equals(request.getIdBlogCategory(), blog.getBlogCategory().getId())) {
            BlogCategory newCategory = blogCategoryRepository.findById(request.getIdBlogCategory())
                    .orElseThrow(() -> new AppException(ErrorCode.BLOG_CATEGORY_NOT_FOUND));
            blog.setBlogCategory(newCategory);
        }

        Set<Long> currentTagIds = blog.getTags().stream()
                .map(Tag::getId)
                .collect(Collectors.toSet());

        Set<Long> newTagIds = request.getListTags() == null ?
                Collections.emptySet() :
                new HashSet<>(request.getListTags());

        if (!currentTagIds.equals(newTagIds)) {
            blog.getTags().clear();
            if (!newTagIds.isEmpty()) {
                List<Tag> tags = tagRepository.findAllById(newTagIds);
                if (tags.size() != newTagIds.size()) {
                    throw new AppException(ErrorCode.TAG_NOT_FOUND);
                }
                blog.getTags().addAll(tags);
            }
        }

        return blogMapper.toResponse(blogRepository.save(blog));
    }

    public void togglePublished(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.BLOG_NOT_FOUND));

        blog.setPublished(!blog.getPublished());
        blogRepository.save(blog);
    }

    public BlogResponse getById(Long id) {
        Blog blog = blogRepository.findById(id)
                .orElseThrow(()-> new AppException(ErrorCode.BLOG_NOT_FOUND));
        return blogMapper.toResponse(blog);
    }

    public PaginationResponse<BlogResponse> getFilterBlogs(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        Page<Blog> blogs = blogRepository.findAll(pageable);
        return new PaginationResponse<BlogResponse>(
                blogs.getContent().stream().map(blogMapper::toPreView).toList(),
                blogs.getNumber(),
                blogs.getTotalPages(),
                blogs.getTotalElements()
        );
    }
}
