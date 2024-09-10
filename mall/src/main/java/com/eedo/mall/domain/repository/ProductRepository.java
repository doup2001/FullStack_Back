package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.Product;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @EntityGraph(attributePaths = "imageList")
    @Query("select p from Product p where p.pno = :pno")
    Optional<Product> selectOne(@Param("pno") Long pno);

    @Modifying // 수정하거나 삭제할때 사용
    @Query("update Product p set p.delFlag = :delFlag where p.pno =:pno")
    void updateToDelete(@Param("pno") Long pno, @Param("delFlag") boolean delFlag);


}
