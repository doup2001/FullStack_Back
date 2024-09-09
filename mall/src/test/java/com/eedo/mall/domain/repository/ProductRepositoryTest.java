package com.eedo.mall.domain.repository;

import com.eedo.mall.domain.entity.Product;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
        product.addImageString("IMAGE_TEST.png");
        productRepository.save(product);

        //then

        //성공 케이스 작성
        product.getImageList().forEach(image->{
            Assertions.assertThat(image.getFileName()).isEqualTo("IMAGE_TEST.png");
        });





    }

}