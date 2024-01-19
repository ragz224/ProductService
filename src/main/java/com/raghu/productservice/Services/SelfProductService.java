package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.ProductNotFoundException;
import com.raghu.productservice.Mappers.DTOMapper;
import com.raghu.productservice.Models.Category;
import com.raghu.productservice.Models.Product;
import com.raghu.productservice.Repository.CategoryRepo;
import com.raghu.productservice.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("selfProductService")
public class SelfProductService implements ProductService{

    ProductRepo productRepo;
    CategoryRepo categoryRepo;
    @Autowired
    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
        this.productRepo = productRepo;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto genericProductDto) {
        Category category = new Category();


        Product product = DTOMapper.GenericToProductDtoMapper(genericProductDto);
        Optional<Category> Opscategory =  categoryRepo.findByName(genericProductDto.getCategory().getName());
        if(Opscategory.isEmpty()) {
            throw new RuntimeException();
        }
        Category savedCategory = Opscategory.get();
        product.setCategory(savedCategory);

        Product savedProduct =  productRepo.save(product);

        return DTOMapper.ProducttoGenericDtoMapper(savedProduct);
    }

    @Override
    public GenericProductDto getProductById(UUID uuid) {
        Product Savedproduct = productRepo.findById(uuid).get();
        return DTOMapper.ProducttoGenericDtoMapper(Savedproduct);
    }

    @Override
    public GenericProductDto UpdateProductById(UUID uuid, GenericProductDto genericProductDto) {
        Optional<Product> Opsproduct = productRepo.findById(uuid);

        Product savedProduct = Opsproduct.get();
        savedProduct.setTitle(genericProductDto.getTitle());
        savedProduct.setImage(genericProductDto.getImage());
        savedProduct.setPrice(genericProductDto.getPrice());
        savedProduct.setDescription(genericProductDto.getDescription());
        savedProduct.setCategory(genericProductDto.getCategory());
//
//        Category category = genericProductDto.getCategory();
//        savedProduct.setCategory(category);
//
//        category.getProduct().add(savedProduct);

        return DTOMapper.ProducttoGenericDtoMapper(savedProduct);
    }


}
