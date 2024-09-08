package com.eedo.mall.domain.controller;

import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final CustomFileUtil FileUtil;


    @PostMapping
    public Map<String, String> register(ProductDTO productDTO) {

        log.info("[MyLOG] : register " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = FileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        return Map.of("Result", "SUCCESS");

    }


}
