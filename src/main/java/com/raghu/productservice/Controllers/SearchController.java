package com.raghu.productservice.Controllers;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.DTOs.SearchRequestDto;
import com.raghu.productservice.Services.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {
    private SearchService searchService;


    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }


    @PostMapping
    public Page<GenericProductDto> getAllPages(@RequestBody SearchRequestDto searchRequestDto) {
           return searchService.getAllPages(searchRequestDto.getQuery(), searchRequestDto.getPagesize(),
                   searchRequestDto.getPageNumber(), searchRequestDto.getSortValue());
    }
}
