package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.InvalidCategoryException;
import com.raghu.productservice.Exceptions.NotFoundException;
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
    public GenericProductDto createProduct(GenericProductDto genericProductDto) throws InvalidCategoryException {

        Product product = DTOMapper.GenericToProductDtoMapper(genericProductDto);
        String category = genericProductDto.getCategory();
        if (category == null) {
            throw new RuntimeException("Category not provided");
        }
        Optional<Category> Opscategory =  categoryRepo.findByName(genericProductDto.getCategory());
        if(Opscategory.isEmpty()) {
            throw new InvalidCategoryException("The category: " + genericProductDto.getCategory() + " doesn't exists.");
        }
        Category category1 = Opscategory.get();
        product.setCategory(category1);

        Product savedProduct = productRepo.save(product);

        return DTOMapper.ProducttoGenericDtoMapper(savedProduct);
    }

    @Override
    public GenericProductDto getProductById(String id) {
        Product Savedproduct = productRepo.findById(UUID.fromString(id)).get();
        return DTOMapper.ProducttoGenericDtoMapper(Savedproduct);
    }

    @Override
    public GenericProductDto UpdateProductById( GenericProductDto genericProductDto, String id) throws NotFoundException {
        Optional<Product> Opsproduct = productRepo.findById(UUID.fromString(id));
        if(Opsproduct.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        Product product = Opsproduct.get();
        Optional<Category> OpsCategory = categoryRepo.findByName(genericProductDto.getCategory());
        if(OpsCategory.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        Category category = OpsCategory.get();

        product.setDescription(genericProductDto.getDescription());
        product.setTitle(genericProductDto.getTitle());
        product.setImage(genericProductDto.getImage());
        product.setPrice(genericProductDto.getPrice());
        product.setCategory(category);
        Product savedProduct = productRepo.save(product);

        return DTOMapper.ProducttoGenericDtoMapper(product);


    }

    public GenericProductDto deletProductById(String id) throws NotFoundException {
        Optional<Product> opsProduct = productRepo.findById(UUID.fromString(id));
        if(opsProduct.isEmpty()) {
            throw new NotFoundException("Not found");
        }
        productRepo.deleteById(UUID.fromString(id));
        Product product = opsProduct.get();
        return DTOMapper.ProducttoGenericDtoMapper(product);

    }


}
