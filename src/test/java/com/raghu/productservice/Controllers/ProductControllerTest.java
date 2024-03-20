package com.raghu.productservice.Controllers;

import com.raghu.productservice.Controllers.ProductController;
import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import com.raghu.productservice.Services.ProductService;
import jakarta.persistence.SqlResultSetMapping;
import lombok.Getter;
import lombok.Setter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import java.util.Random;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Getter
@Setter
@SpringBootTest
public class ProductControllerTest {

    @Autowired
    ProductController productController;

    @MockBean
    ProductService productServiceMock;

//    public ProductControllerTest() {
//        productServiceMock = Mockito.mock(ProductService.class);
//        productController = new ProductController(productServiceMock);
//    }

//    @Test
//    public void testGetproductByIdreturnEmptyObjectWhenNoProductIsFound() throws NotFoundException {
//        when(productServiceMock.getProductById(any(String.class))).thenReturn(null);
//
//        GenericProductDto response = productController.GetProductById(null);
////        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
////        Assertions.assertNull(response.getBody());
//
//        Assertions.assertNotNull(response);
//    }


//    @Test
//    public void testGetProductByIdWhenprodctisFound() {
//        GenericProductDto expectedOutPut = new GenericProductDto();
//        expectedOutPut.setId("yyy");
//        expectedOutPut.setTitle("date");
//
//
//        when(productServiceMock.getProductById(any())).thenReturn(expectedOutPut);
//
//        GenericProductDto response = productController.GetProductById("3a5b1a63-1d7b-4435-9384-8fd289a0ea14");
//        System.out.println(response);
//        Assertions.assertEquals( response.getId(),expectedOutPut.getId());
//        Assertions.assertEquals(response.getTitle(),expectedOutPut.getTitle());
//    }


    @Test
    public void testGetProductByIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDto toBeReturned = new GenericProductDto();
        toBeReturned.setId("1L");
        toBeReturned.setTitle("iPhone");

        when(productServiceMock.getProductById(String.valueOf("1L")))
                .thenReturn(toBeReturned);

        GenericProductDto response = productController.GetProductById("1L");

//        Assertions.assertEquals(toBeReturned,response);
//        Assertions.assertEquals("1L", "1L");
       Assertions.assertEquals("iPhone",response.getTitle());

    }

    @Test
    public  void testUpdateproductByIdwhenitNull() throws NotFoundException {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setPrice("56565");
        genericProductDto.setId("2343");
        when(productServiceMock.UpdateProductById(any(GenericProductDto.class),any())).thenReturn(null);
        ResponseEntity<GenericProductDto> response = productController.UpdateProductById("1L",genericProductDto);
        Assertions.assertNull(response);
    }

    @Test
    public void testUpdateProductByIdwhenIdReturnsCorrectResponse() throws NotFoundException {
        GenericProductDto returngenericProductDto = new GenericProductDto();
        returngenericProductDto.setPrice("56565");
        returngenericProductDto.setId("2343");

        when(productServiceMock.UpdateProductById(any(GenericProductDto.class), any(String.class))).thenReturn(returngenericProductDto);

        ResponseEntity<GenericProductDto> response = productController.UpdateProductById("1L", new GenericProductDto());
        Assertions.assertEquals(returngenericProductDto,response.getBody());


    }



}
