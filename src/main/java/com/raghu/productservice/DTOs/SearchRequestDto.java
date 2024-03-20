package com.raghu.productservice.DTOs;

import com.raghu.productservice.Models.SortValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {
    private String query;
    private int pagesize;
    private int pageNumber;
    private SortValue sortValue;
}
