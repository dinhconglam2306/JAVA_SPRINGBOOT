package com.zendvn.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Articles;
import com.zendvn.shop.model.Authors;
import com.zendvn.shop.model.Categories;

import java.util.List;


public interface ArticlesRepository extends JpaRepository<Articles, Long>, JpaSpecificationExecutor<Articles> {
    Page<Articles> findByStatus(Status status, Pageable pageable);

    @Query("SELECT a FROM Articles a WHERE a.special = true AND a.status = ACTIVE")
    List<Articles> findSpecialArticles();

    long countByStatus(Status status);

    List<Articles> findByCategory(Categories category);
    
    Page<Articles> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Articles> findByCategoryAndStatus(Categories category, Status status, Pageable pageable);
    Page<Articles> findByAuthorAndStatus(Authors author, Status status, Pageable pageable);
}
