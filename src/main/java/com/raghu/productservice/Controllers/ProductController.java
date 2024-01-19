package com.raghu.productservice.Controllers;

import com.raghu.productservice.DTOs.ExceptionDto;
import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import com.raghu.productservice.Exceptions.ProductNotFoundException;
import com.raghu.productservice.Models.Product;
import com.raghu.productservice.Services.FakeStoreProductService;
import com.raghu.productservice.Services.ProductService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/Products")
public class ProductController {

    ProductService productService;


    @Autowired
    public ProductController(  @Qualifier("selfProductService") ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public List<GenericProductDto> getProducts() {
//        return productService.getAllProducts();
//
//
//    }
//
//    @GetMapping("{id}")
//    public GenericProductDto getProductsById(@PathVariable("id") Long id) throws NotFoundException {
//
//        return productService.getProductById(id);
//
//    }
//
//    @PutMapping("{id}")
//    public GenericProductDto updateProductById(@PathVariable("id") Long id, @RequestBody  GenericProductDto genericProductDto) {
//        return productService.updateProductById(id,genericProductDto);
//    }
//
//    @PostMapping()
//    public GenericProductDto createProduct(@RequestBody  GenericProductDto genericProductDto) {
//        return productService.uploadProduct(genericProductDto);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<GenericProductDto> DeleteProductById(@PathVariable Long id) {
//        return new ResponseEntity<>(productService.DeleteProductById(id), HttpStatus.OK);
//    }


//    @ExceptionHandler(NotFoundException.class)
//    public ResponseEntity<ExceptionDto> handleNotFoundException(NotFoundException notFoundException) {
//        return new ResponseEntity<>( new ExceptionDto(HttpStatus.NOT_FOUND,notFoundException.getMessage()),
//        HttpStatus.NOT_FOUND);
//    }


    @PostMapping()
    public ResponseEntity<GenericProductDto> CreateProduct(@RequestBody GenericProductDto genericProductDto) {
        return new ResponseEntity<>(productService.createProduct(genericProductDto),HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<GenericProductDto> GetProductById(@PathVariable("id") UUID uuid) {
        return new ResponseEntity<>(productService.getProductById(uuid), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<GenericProductDto> UpdateProductById(@PathVariable("id") UUID uuid,
                                                               @RequestBody GenericProductDto genericProductDto) {
        return new ResponseEntity<>(productService.UpdateProductById(uuid, genericProductDto),HttpStatus.OK);

    }


}
