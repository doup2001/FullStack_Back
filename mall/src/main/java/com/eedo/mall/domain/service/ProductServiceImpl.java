package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.entity.ProductImage;
import com.eedo.mall.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Override
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {

        log.info("=== getList ===");

        Pageable pageable = PageRequest.of(pageRequestDTO.getPage()-1, pageRequestDTO.getSize(), Sort.by("pno").descending());

        Page<Object[]> result = productRepository.selectList(pageable);

        log.info(result);

        List<ProductDTO> dtoList = result.get().map(arr -> {

            Product product = (Product) arr[0];
            ProductImage productImage = (ProductImage) arr[1];

            String imageStr = productImage.getFileName();
            ProductDTO dto = entityToDTO(product);
            dto.setUploadFileNames(Collections.singletonList(imageStr));

            return dto;
        }).toList();


        long total = result.getTotalElements();

        return new PageResponseDTO<ProductDTO>(dtoList, pageRequestDTO, total);
    }

    @Override
    public Long register(ProductDTO productDTO) {
        log.info("=== register ===");

        Product product = dtoToEntity(productDTO);

        // getUploadFileNames()가 null일 경우 빈 리스트로 초기화
        List<String> uploadFileNames = productDTO.getUploadFileNames();
        if (uploadFileNames != null) {
            for (String arr : uploadFileNames) {
                product.addImageString(arr);
            }
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
