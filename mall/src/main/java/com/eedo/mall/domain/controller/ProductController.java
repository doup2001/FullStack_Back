package com.eedo.mall.domain.controller;

import com.eedo.mall.domain.dto.PageRequestDTO;
import com.eedo.mall.domain.dto.PageResponseDTO;
import com.eedo.mall.domain.dto.ProductDTO;
import com.eedo.mall.domain.service.ProductService;
import com.eedo.mall.util.CustomFileUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final CustomFileUtil FileUtil;
    private final ProductService productService;


    @PostMapping
    public Map<String, String> register(ProductDTO productDTO) {

        log.info("[My LOG] : register " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = FileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        log.info("[MY LOG] : loadFileNames: "+ productDTO.getUploadFileNames());

        productService.register(productDTO);
        return Map.of("Result", "SUCCESS");

    }

    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String filename) {
        return FileUtil.getFile(filename);
    }

    @DeleteMapping("view/{filename}")
    public Map<String, String> deleteFile(@PathVariable List<String> filename) {
        FileUtil.delelteFiles(filename);

        return Map.of("Result", "DELETE" + filename + "Success");
    }


    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        return productService.getList(pageRequestDTO);
    }

}
