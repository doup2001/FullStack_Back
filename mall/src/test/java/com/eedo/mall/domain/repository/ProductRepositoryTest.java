package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.Product;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
@Log4j2
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testInsert() throws Exception{
        //given
        Product product = Product.builder()
                .pname("test")
                .price(1000)
                .pdesc("test_Product")
                .delFlage(false)
                .build();

        //when
        product.addImageString(UUID.randomUUID()+"_"+"IMAGE_TEST.png");
        productRepository.save(product);

        //then

        //성공 케이스 작성
        product.getImageList().forEach(image->{
            Assertions.assertThat(image.getFileName()).contains("IMAGE_TEST.png");
        });

    }

    @Test
    public void testInsert2() throws Exception{
        //given
        Product product2 = Product.builder()
                .pname("test2")
                .price(3000)
                .pdesc("test2_Product")
                .delFlage(false)
                .build();

        product2.addImageString(UUID.randomUUID() + "_" + "TEST_IMAGE2.png");
        productRepository.save(product2);

        //when
        Optional<Product> product = productRepository.selectOne(product2.getPno());
        Product result = product.orElseThrow();

        //then

        log.info("[MYLOG]:" + result.getImageList());



    }
}