package com.eedo.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long pno;
    private String pname;
    private int price;
    private String pdesc;
    private boolean delFlag; // 삭제 여부를 boolean으로

    @Builder.Default
    private List<String> uploadFileNames = new ArrayList<>();

    @Builder.Default
    private List<MultipartFile> files = new ArrayList<>();



}
