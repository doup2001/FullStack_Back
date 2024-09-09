package com.eedo.mall.domain.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "tbl_product")
@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "imageList")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pno;

    private String pname;

    private int price;

    private String pdesc;

    private boolean delFlage;

    @ElementCollection
    @Builder.Default
    private List<ProductImage> imageList = new ArrayList<>();

    // 변경 메서드
    public void changePname(String pname) {
        this.pname = pname;
    }


    public void changePrice(int price) {
        this.price = price;
    }


    public void changePdesc(String pdesc) {
        this.pdesc = pdesc;
    }

    public void changeToDelete(boolean delFlage) {
        this.delFlage = delFlage;
    }

    // 비즈니스 로직

    public void addImage(ProductImage image) {
        image.setOrd(imageList.size());
        imageList.add(image);
    }

    public void addImageString(String fileName) {
        ProductImage productImage = ProductImage.builder()
                .fileName(fileName)
                .ord(imageList.size())
                .build();

        imageList.add(productImage);

        //addImage(productImage)
    }

    public void clearList() {
        this.imageList.clear();
    }

}
