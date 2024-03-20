package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import com.raghu.productservice.Mappers.DTOMapper;
import com.raghu.productservice.Models.Product;
import com.raghu.productservice.Repository.ProductRepo;
import com.raghu.productservice.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({ DTOMapper.class })
public class ProductServiceTest {

    @Autowired
    ProductService productService;

    @MockBean
    ProductRepo productMockRepo;

    @Test
    public void getProductByIdWhenProductisNotFound() throws NotFoundException {
        when(productMockRepo.findById(any())).thenReturn(null);

        GenericProductDto response = productService.getProductById("5d9fddd8-e8ea-407e-aad8-0d730e5825aa");
        Assertions.assertNull(response);
    }

    @Test
    public void getProductByIdwhenproductisfound() throws NotFoundException {

        Product product = new Product(); // Assuming you have a product instance
        UUID uuid = UUID.fromString("f2b71d79-7239-4491-adaa-a75b3d999966");
        product.setUuid(uuid); // Set the UUID
        product.setTitle("rtrt");



        Optional<Product> expectedProduct = Optional.of(product);

        when(productMockRepo.findById(any())).thenReturn(expectedProduct);

//        // Mocking static method
//        GenericProductDto expectedDto = new GenericProductDto();
//        expectedDto.setId("f2b71d79-7239-4491-adaa-a75b3d999966");
//        expectedDto.setTitle("rtrt");
//        // Add other fields as per your mapping logic


//        // Partially mock the static method
//        DTOMapper mapperSpy = spy(DTOMapper.class);
//        doReturn(expectedDto).when(mapperSpy).ProducttoGenericDtoMapper(any(Product.class));
//
//        Power.mockStatic(DTOMapper.class);
//        when(DTOMapper.ProducttoGenericDtoMapper(product)).thenReturn(expectedDto);
//


        GenericProductDto response = productService.getProductById("f2b71d79-7239-4491-adaa-a75b3d999966");
        Assertions.assertEquals(product.getUuid().toString(),response.getId());


    }


}
