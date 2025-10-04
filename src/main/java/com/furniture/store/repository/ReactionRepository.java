package com.furniture.store.repository;

import com.furniture.store.dto.response.ReactionSummaryResponse;
import com.furniture.store.entity.Reaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReactionRepository extends JpaRepository<Reaction, Long> {
    Optional<Reaction> findByUserIdAndBlogId(String userId, Long blogId);
    @Query("""
                SELECT r.type AS type, COUNT(r) AS count
                FROM Reaction r
                WHERE r.blog.id = :blogId
                GROUP BY r.type
            """)
    List<ReactionSummaryResponse> findReactionSummaryByBlogId(@Param("blogId") Long blogId);
    Page<Reaction> findByBlogId(Pageable pageable, Long blogId);
}
