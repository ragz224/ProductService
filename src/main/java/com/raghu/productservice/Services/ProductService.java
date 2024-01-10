package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;

import java.util.List;

public interface ProductService {

    GenericProductDto getProductById(Long id) throws NotFoundException;

    GenericProductDto uploadProduct(GenericProductDto genericProductDto);

    GenericProductDto updateProductById(Long id,GenericProductDto genericProductDto);

     GenericProductDto DeleteProductById(Long id);

    List<GenericProductDto> getAllProducts();


}
