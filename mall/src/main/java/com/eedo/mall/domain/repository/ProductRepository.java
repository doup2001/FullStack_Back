package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product, Long> {

//    @Query("select p from Product where pno = :pno")
//    ProductDTO selectOne(@Param("pno") Long pno);



}
