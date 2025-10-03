package com.furniture.store.mapper;

import com.furniture.store.dto.request.BlogRequest;
import com.furniture.store.dto.response.BlogResponse;
import com.furniture.store.entity.Blog;
import com.furniture.store.utils.SlugUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BlogMapper {
    UserMapper userMapper;
    TagMapper tagMapper;
    BlogCategoryMapper blogCategoryMapper;

    public Blog toEntity(BlogRequest request) {
        return Blog.builder()
                .title(request.getTitle())
                .slug(SlugUtils.generateSlug(request.getTitle()))
                .content(request.getContent())
                .summary(request.getSummary())
                .thumbnail(request.getThumbnail())
                .build();
    }

    public BlogResponse toPreView(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .slug(blog.getSlug())
                .title(blog.getTitle())
                .thumbnail(blog.getThumbnail())
                .summary(blog.getSummary())
                .BlogCategory(blogCategoryMapper.toPreView(blog.getBlogCategory()))
                .published(blog.getPublished())
                .author(userMapper.toPreView(blog.getAuthor()))
                .listTags(blog.getTags() == null ? Set.of() :
                        blog.getTags().stream().map(tagMapper::toResponse).collect(Collectors.toSet()))
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }

    public BlogResponse toResponse(Blog blog) {
        return BlogResponse.builder()
                .id(blog.getId())
                .slug(blog.getSlug())
                .title(blog.getTitle())
                .content(blog.getContent())
                .thumbnail(blog.getThumbnail())
                .summary(blog.getSummary())
                .published(blog.getPublished())
                .author(userMapper.toPreView(blog.getAuthor()))
                .BlogCategory(blogCategoryMapper.toPreView(blog.getBlogCategory()))
                .listTags(blog.getTags() == null ? Set.of() :
                        blog.getTags().stream().map(tagMapper::toResponse).collect(Collectors.toSet()))
                .createdAt(blog.getCreatedAt())
                .updatedAt(blog.getUpdatedAt())
                .build();
    }

    public void update(Blog blog, BlogRequest request) {
        if (!blog.getTitle().equals(request.getTitle())) {
            blog.setTitle(request.getTitle());
            blog.setSlug(SlugUtils.generateSlug(request.getTitle()));
        }

        if (request.getSummary() != null && !request.getSummary().equals(blog.getSummary())) {
            blog.setSummary(request.getSummary());
        }

        if (request.getContent() != null && !request.getContent().equals(blog.getContent())) {
            blog.setContent(request.getContent());
        }

        if (request.getThumbnail() != null && !request.getThumbnail().equals(blog.getThumbnail())) {
            blog.setThumbnail(request.getThumbnail());
        }
    }
}
