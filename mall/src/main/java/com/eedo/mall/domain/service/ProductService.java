package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.entity.ProductImage;

import java.util.List;

public interface ProductService {

    // 리스트 불러오기
    PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO);

    // 저장하기
    Long register(ProductDTO productDTO) throws InterruptedException;

    ProductDTO get(Long pno);

    //수정 및 삭제
    void modify(ProductDTO productDTO);

    void delete(Long pno);

    default ProductDTO entityToDTO(Product product) {

        ProductDTO productDTO = ProductDTO.builder()
                .pno(product.getPno())
                .pname(product.getPname())
                .pdesc(product.getPdesc())
                .price(product.getPrice())
                .delFlag(product.isDelFlag())
                .build();

        List<ProductImage> imageList = product.getImageList();
        if (imageList.isEmpty()) {
            return productDTO;
        }

        List<String> fileName = imageList.stream().map(
                productImage -> productImage.getFileName()).toList();

        productDTO.setUploadFileNames(fileName);

        return productDTO;
    }


    default Product dtoToEntity(ProductDTO productDTO) {

        return Product.builder()
                .pno(productDTO.getPno())
                .pname(productDTO.getPname())
                .pdesc(productDTO.getPdesc())
                .price(productDTO.getPrice())
                .delFlag(productDTO.isDelFlag())
                .build();
    }
}
