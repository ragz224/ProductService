package com.raghu.productservice.Controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raghu.productservice.Controllers.ProductController;
import com.raghu.productservice.Controllers.ProductController;
import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import com.raghu.productservice.Services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.mockito.Mockito.when;


import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMvcTest {

    @Autowired
   ProductController productController;

    @MockBean
    ProductService productService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void testProductByIdwhenApiReturn() throws Exception {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setTitle("Electronics");
        genericProductDto.setId("1l");
        genericProductDto.setPrice("99999");
        when(productService.getProductById("1l")).thenReturn(genericProductDto);



        ResultActions resultActions =  mockMvc.perform(get("/Products/1l")).andExpect(status().is(200));
//                .andExpect((ResultMatcher) content().json("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}"))
//                .andExpect(jsonPath("$.id").value(1l));
//                .andExpect((ResultMatcher) content().json("{\"id\":1,\"title\":\"iPhone\",\"description\":null,\"image\":null,\"category\":\"electronics\",\"price\":0.0}"))
//                .andExpect(jsonPath("$.id").value(1l));
        String responseString = resultActions.andReturn().getResponse().getContentAsString(); // it will return all the data in resultaction
        System.out.println(responseString);
        GenericProductDto responseDto = objectMapper.readValue(responseString, GenericProductDto.class);
        System.out.println(responseDto);

        Assertions.assertNotNull(responseDto);
        Assertions.assertEquals(genericProductDto.getId(),responseDto.getId());
//        Assertions.assertEquals(1l, responseDto.getId());



    }



}
