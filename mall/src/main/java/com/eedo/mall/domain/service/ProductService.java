package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;

public interface ProductService {

    // 리스트 불러오기
    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    // 저장하기
    Long register(ProductDTO productDTO);

    ProductDTO get(Long pno);

    default ProductDTO entityToDTO(Product product) {

        return ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .delFlag(product.isDelFlag())
                .build();
    }

    default Product dtoToEntity(ProductDTO productDTO) {

        return Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .delFlag(productDTO.isDelFlag())
                .build();
    }

}
