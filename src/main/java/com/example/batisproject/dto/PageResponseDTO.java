package com.example.batisproject.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class PageResponseDTO<E> {

    private int page;
    private int size;
    private int total;

    private int start;
    private int end;

    private boolean prev;
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total){

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int)(Math.ceil(this.page / 10.0)) * 10;
        this.start = end - 9;

        int last = (int)(Math.ceil(total / (double)size));

        this.end = end > last ? last : end;

        this.prev = this.start > 1;

        this.next = total > this.end * this.size;

    }
}
