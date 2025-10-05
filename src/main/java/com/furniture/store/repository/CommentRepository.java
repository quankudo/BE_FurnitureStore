package com.furniture.store.repository;

import com.furniture.store.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByParentIsNullAndBlogId(Pageable pageable, Long blogId);
}
