package com.eedo.mall.domain.service;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.entity.Product;
import com.eedo.mall.domain.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
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
    public void testRegister() throws Exception{
        //given
        ProductDTO productDTO = ProductDTO.builder()
                .pname("test")
                .price(10000)
                .delFlag(false)
                .pdesc("TestProduct_Register")
                .build();
        //when

        List<String> uploadFileNames = List.of(new String[]{UUID.randomUUID()+"_"+"ImageRegister"});
        productDTO.setUploadFileNames(uploadFileNames);
        Long registerID = productService.register(productDTO);

        //then

        Optional<Product> expect = productRepository.findById(registerID);
        Product expectProduct = expect.orElseThrow();

        Assertions.assertThat(expectProduct.getPno()).isSameAs(registerID);
    }
    @Test
    public void testGetList() throws Exception{
        //given

        for (int i = 0; i < 13; i++) {
            ProductDTO dto = ProductDTO.builder()
                    .pname("testByList " + i)
                    .price(Integer.parseInt(i + "000"))
                    .pdesc("test_List_Product " + i)
                    .delFlag(false)
                    .uploadFileNames(Collections.singletonList(UUID.randomUUID() + "-" + "ListTest"+i))
                    .build();

            productService.register(dto);
        }
        //when

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        //then
        PageResponseDTO<ProductDTO> responseDTO = productService.getList(pageRequestDTO);
        log.info(responseDTO);

    }
}