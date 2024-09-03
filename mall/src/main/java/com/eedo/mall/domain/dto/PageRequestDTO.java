package com.eedo.mall.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import lombok.extern.slf4j.Slf4j;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class PageRequestDTO {
    @Builder.Default
    private int page = 1;

    @Builder.Default
    private int size = 10;


}
