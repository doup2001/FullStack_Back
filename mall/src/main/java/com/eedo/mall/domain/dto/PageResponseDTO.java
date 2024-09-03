package com.eedo.mall.domain.dto;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import javax.lang.model.element.Element;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Builder(builderMethodName = "withAll")
@Slf4j
public class PageResponseDTO {

    private List<Element> dtolist;
    private List<Integer> pageNumList;

    private PageRequestDTO pageRequestDTO;

    private boolean prev, next;

    private int totalCount;
    private int prevPage;
    private int nextPage;
    private int totalPage;
    private int current;


    public PageResponseDTO(List<Element> dtolist, PageRequestDTO pageRequestDTO, Long total) {
        this.dtolist = dtolist;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = Math.toIntExact(total);

        // 끝페이지부터
        int end = (int) Math.ceil((pageRequestDTO.getPage() / 10.0)) * 10;
        int start = end - 9;

        //진짜 마지막 페이지
        int last = (int) Math.ceil(totalCount / (double) pageRequestDTO.getSize());

        end = end > last ? end : last;

        this.prev = start > 1;
        this.next = totalCount > end * pageRequestDTO.getSize();
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        this.prevPage = prev ? start - 1 : 0;
        this.nextPage = next ? end + 1 : 0;

    }




}
