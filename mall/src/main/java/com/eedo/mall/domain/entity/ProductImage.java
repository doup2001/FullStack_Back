package com.eedo.mall.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString()
public class ProductImage {

    private String fileName;
    private int ord;

    public void setOrd(int ord) {
        this.ord = ord;
    }

}
