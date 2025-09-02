package com.zendvn.shop.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.zendvn.shop.enums.Status;
import com.zendvn.shop.model.Items;

public interface ItemsRepository extends JpaRepository<Items, Long>, JpaSpecificationExecutor<Items> {
    Page<Items> findByStatus(Status status, Pageable pageable);

    long countByStatus(Status status);
    
    Page<Items> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
