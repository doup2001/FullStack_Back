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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("api/products")
public class ProductController {

    private final CustomFileUtil FileUtil;
    private final ProductService productService;

    @GetMapping("/view/{filename}")
    public ResponseEntity<Resource> viewFileGet(@PathVariable String filename) {
        return FileUtil.getFile(filename);
    }

    @DeleteMapping("view/{filename}")
    public Map<String, String> deleteFile(@PathVariable List<String> filename) {
        FileUtil.delelteFiles(filename);

        return Map.of("Result", "DELETE" + filename + "Success");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')") //임시로 권한 설정
    @GetMapping("/list")
    public PageResponseDTO<ProductDTO> getList(PageRequestDTO pageRequestDTO) {
        return productService.getList(pageRequestDTO);
    }

    @PostMapping
    public Map<String, Long> register(ProductDTO productDTO) throws InterruptedException {

        log.info("[My LOG] : register " + productDTO);

        List<MultipartFile> files = productDTO.getFiles();
        List<String> uploadFileNames = FileUtil.saveFiles(files);

        productDTO.setUploadFileNames(uploadFileNames);

        log.info("[MY LOG] : loadFileNames: " + productDTO.getUploadFileNames());

        Long register = productService.register(productDTO);
        return Map.of("Result", register);

    }

    //조회
    @GetMapping("/{pno}")
    public ProductDTO getOne(@PathVariable Long pno) {

        ProductDTO dto = productService.get(pno);

        return dto;
    }

    //수정 및 삭제
    @PutMapping("/{pno}")
    public Map<String, String> PutModify(@PathVariable Long pno, ProductDTO productDTO) {

        ProductDTO oldProductDTO = productService.get(pno);
        log.info("[old]" + oldProductDTO);

        productDTO.setPno(pno);

        //파일 저장
        List<MultipartFile> files = productDTO.getFiles();
        List<String> currentFileUploadFileNames = FileUtil.saveFiles(files);

        //기존 저장할 파일 이름
        List<String> uploadFileNames = productDTO.getUploadFileNames();
        if (currentFileUploadFileNames != null) {
            uploadFileNames.addAll(currentFileUploadFileNames);
        }

        // 위 내용 까지는 imageList에 사진이 저장되어있다.
        List<String> oldUploadFileNames = oldProductDTO.getUploadFileNames();

        log.info("[old]:" + oldUploadFileNames);

        if (oldUploadFileNames != null) {
            List<String> removeFiles =  oldUploadFileNames
                    .stream()
                    .filter(fileName -> uploadFileNames.indexOf(fileName) == -1).collect(Collectors.toList());

            FileUtil.delelteFiles(removeFiles);
        }

        productService.modify(productDTO);

        return Map.of("Result", "Success");
    }

    @DeleteMapping("/{pno}")
    public ProductDTO remove(@PathVariable Long pno) {
        productService.delete(pno);
        return productService.get(pno);
    }
}
