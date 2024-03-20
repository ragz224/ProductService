package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Models.Price;
import com.raghu.productservice.Models.Product;
import com.raghu.productservice.Models.SortValue;
import com.raghu.productservice.Repository.ProductRepo;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {
    private ProductRepo productRepo;

    public SearchService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    public Page<GenericProductDto> getAllPages(String query, int pageSize, int pageNumber, SortValue sortValue) {

//        Sort sort = Sort.by("title").descending().
//                and(Sort.by("description"));
        Sort sort;
        if("desc".equals(sortValue.getSortType())) {

            sort = Sort.by(sortValue.getSortvalue()).descending();
        }
        else {

            sort = Sort.by(sortValue.getSortvalue()).ascending();
        }

        Pageable pageable = PageRequest.of(pageNumber,pageSize,sort);
        Page<Product> productPage = productRepo.findAllByTitleContaining(query, pageable);
        List<Product> products = productPage.get().toList();
        List<GenericProductDto> productPage1 = new ArrayList<>();

        for(Product product: products) {
            productPage1.add(pageToGenericDtoMapper(product));

        }
        Page<GenericProductDto> pageGenDto = new PageImpl<GenericProductDto>(productPage1,productPage.getPageable(),
                productPage.getTotalElements());

        return pageGenDto;
    }

    public GenericProductDto pageToGenericDtoMapper (Product product) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle(product.getTitle());
        genericProductDto.setPrice(product.getPrice().getName());
        genericProductDto.setPrice(new Price().getName());
        genericProductDto.setDescription(product.getDescription());
        genericProductDto.setImage(product.getImage());
        return  genericProductDto;
    }
}
