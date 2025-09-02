package com.zendvn.shop.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Articles;
import com.zendvn.shop.model.Authors;


public interface AuthorsRepository extends JpaRepository<Authors, Long>, JpaSpecificationExecutor<Authors> {
    Page<Authors> findByStatus(Status status, Pageable pageable);

    long countByStatus(Status status);
    boolean existsBySlug(String slug);
    boolean existsBySlugAndIdNot(String slug, Long id);
    Optional<Authors> findBySlug(String slug);

    Page<Authors> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Page<Articles> findByAuthorAndStatus(Articles author, Status status, Pageable pageable);
}
