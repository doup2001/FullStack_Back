package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        log.info("=== getList ===");

        return null;
    }

    @Override
    public Long register(ProductDTO productDTO) {
        log.info("=== register ===");

        Product product = dtoToEntity(productDTO);

        for (String arr : productDTO.getUploadFileNames()) {
            product.addImageString(arr);
        }
        return productRepository.save(product).getPno();
    }

    @Override
    public ProductDTO get(Long pno) {
        Optional<Product> product = productRepository.findById(pno);
        Product result = product.orElseThrow();
        return entityToDTO(result);
    }

}
