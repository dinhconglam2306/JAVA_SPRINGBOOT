package com.zendvn.shop.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Categories;

public interface CategoriesRepository extends JpaRepository<Categories, Long>, JpaSpecificationExecutor<Categories> {
    Page<Categories> findByStatus(Status status, Pageable pageable);

    long countByStatus(Status status);

    @Query("SELECT a FROM Categories a WHERE a.isHomePage = true")
    List<Categories> findCategoriesbyStatus();

    Optional<Categories> findBySlug(String slug);
    
    Page<Categories> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
