package com.eedo.mall.domain.repository.search;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.entity.ProductImage;
import com.eedo.mall.domain.entity.QProduct;
import com.eedo.mall.domain.entity.QProductImage;
import com.querydsl.jpa.JPQLQuery;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.Objects;

@Log4j2
public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch {
    public ProductSearchImpl() {
        super(Product.class);
    }

    // QUeryDSL을 사용해서 적용
    @Override
    public PageResponseDTO<ProductDTO> selectList(PageRequestDTO pageRequestDTO) {

        log.info("---- selectList In QueryDSL----");

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()
                , pageRequestDTO.getSize()
                , Sort.by("pno").descending());

        QProduct product = QProduct.product;
        QProductImage productImage = QProductImage.productImage;

        JPQLQuery<Product> query = from(product);
        query.leftJoin(product.imageList, productImage);
        query.where(productImage.ord.eq(0));

        Objects.requireNonNull(getQuerydsl()).applyPagination(pageable,query);

        List<Product> productList = query.fetch();
        long count = query.fetchCount();
        return null;
    }
}
