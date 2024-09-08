package com.eedo.mall.util;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Size;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@Log4j2
@RequiredArgsConstructor
public class CustomFileUtil {

    @Value("${spring.file.upload.path}") // 프레임워크 value를 사용해야한다.
    private String uploadPath;

    @PostConstruct // 폴더 없으면 생성!
    public void init() {
        File tempFolder = new File(uploadPath);
        if (!tempFolder.exists()){
            tempFolder.mkdir();
        }

        uploadPath = tempFolder.getAbsolutePath();
        log.info(uploadPath);
    }



    public List<String> saveFiles(List<MultipartFile> files) throws RuntimeException {

        if (files.isEmpty()){
            return List.of();
        }


        List<String> uploadNames = new ArrayList<>();

        for (MultipartFile file : files) {
            String savedName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

            Path savePath = Paths.get(uploadPath, savedName);
            uploadNames.add(savedName);

            try {
                Files.copy(file.getInputStream(), savePath);

                String contentType = file.getContentType();
                if (contentType != null || contentType.startsWith("image")) {

                    // S이름 붙여서 수정
                    Path ThumbnailPath = Paths.get(uploadPath, "s_" + savedName);

                    // 썸네일 200으로 수정
                    Thumbnails.of(savePath.toFile()).size(200, 200).toFile(ThumbnailPath.toFile());

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return null;
    }



}
