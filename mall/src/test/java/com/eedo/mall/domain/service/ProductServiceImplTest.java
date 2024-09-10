package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@SpringBootTest
@Log4j2
class ProductServiceImplTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;
    @Test
    public void register() throws Exception{
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .pname("test")
                .price(10000)
                .delFlag(false)
                .pdesc("TestProduct_Register")
                .build();
        //when

        List<String> uploadFileNames = List.of(new String[]{UUID.randomUUID()+"_"+"ImageTest"});
        productDTO.setUploadFileNames(uploadFileNames);
        Long registerID = productService.register(productDTO);

        //then

        Optional<Product> expect = productRepository.findById(registerID);
        Product expectProduct = expect.orElseThrow();

        Assertions.assertThat(expectProduct.getPno()).isSameAs(registerID);
    }
    @Test
    public void getList() throws Exception{
        //given

        for (int i = 0; i < 13; i++) {
            Product product = Product.builder()
                    .pname("test " + i)
                    .price(Integer.parseInt(i + "000"))
                    .pdesc("test_Product " + i)
                    .delFlag(false)
                    .build();
            product.addImageString(UUID.randomUUID() + "_" + "TEST_IMAGE_" + i + ".png");

            ProductDTO productDTO = productService.entityToDTO(product);
            productService.register(productDTO);
        }
        //when

        //then
    }
}