package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import com.raghu.productservice.Exceptions.ProductNotFoundException;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ProductService {

//    GenericProductDto getProductById(Long id) throws NotFoundException;
//
//    GenericProductDto uploadProduct(GenericProductDto genericProductDto);
//
//    GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto);
//
//     GenericProductDto DeleteProductById(Long id);
//
//    List<GenericProductDto> getAllProducts();

    GenericProductDto createProduct(GenericProductDto genericProductDto);
    GenericProductDto getProductById(UUID uuid) ;

    GenericProductDto UpdateProductById(UUID uuid, GenericProductDto genericProductDto) ;


}
