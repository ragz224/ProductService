package com.raghu.productservice.Services;

import com.raghu.productservice.DTOs.ExceptionDto;
import com.raghu.productservice.DTOs.FakeStoreProductDto;
import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductService implements ProductService{
    RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public FakeStoreProductService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    private String productUrl = "https://fakestoreapi.com/products/{id}";
    private String productUrlforPost =  "https://fakestoreapi.com/products";
    private String productUrlForUpdate = "https://fakestoreapi.com/products/{id}";
    private String productUrlForAllProducts = "https://fakestoreapi.com/products";

    private String productUrlForDeletingProduct = "https://fakestoreapi.com/products/{id}";



    public GenericProductDto Insertingdata(FakeStoreProductDto fakeStoreProductDto) {
        GenericProductDto genericProductDto = new GenericProductDto();
        genericProductDto.setId(fakeStoreProductDto.getId());
        genericProductDto.setTitle(fakeStoreProductDto.getTitle());
        genericProductDto.setCategory(fakeStoreProductDto.getCategory());
        genericProductDto.setDescription(fakeStoreProductDto.getDescription());
        genericProductDto.setImage(fakeStoreProductDto.getImage());
        genericProductDto.setPrice(fakeStoreProductDto.getPrice());

        return genericProductDto;
    }


    @Override
    public GenericProductDto getProductById(Long id) throws NotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();

            ResponseEntity<FakeStoreProductDto> response = restTemplate.getForEntity(productUrl,
                    FakeStoreProductDto.class, id);

            FakeStoreProductDto fakeStoreProductDto = response.getBody();

            if(fakeStoreProductDto == null) {
                throw new NotFoundException("product Id  " + id + " not found");
            }
            GenericProductDto genericProductDto = Insertingdata(fakeStoreProductDto);


            return genericProductDto;
        }



    public GenericProductDto uploadProduct(GenericProductDto genericProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> response =
                restTemplate.postForEntity(productUrlforPost,genericProductDto,FakeStoreProductDto.class);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto1 = Insertingdata(fakeStoreProductDto);
        return genericProductDto1;

    }



    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto updatedProductDto) {

        RestTemplate restTemplate = restTemplateBuilder.build();
       HttpEntity<GenericProductDto> requestEntity = new HttpEntity<>(updatedProductDto);

        ResponseEntity<FakeStoreProductDto> response =
              restTemplate.exchange(
                      productUrlForUpdate,
                      HttpMethod.PUT, requestEntity,FakeStoreProductDto.class,
                      id
              );
        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto1 =Insertingdata(fakeStoreProductDto);

        return genericProductDto1;


    }


    @Override
        public List<GenericProductDto> getAllProducts() {
            RestTemplate restTemplate = restTemplateBuilder.build();

            ResponseEntity<FakeStoreProductDto[]> response = restTemplate.getForEntity(productUrlForAllProducts, FakeStoreProductDto[].class);


            FakeStoreProductDto[] fakeStoreProductDtoArray = response.getBody();



            List<GenericProductDto> genericProductDtoList = new ArrayList<>();

            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoArray) {
                GenericProductDto genericProductDto =Insertingdata(fakeStoreProductDto);

                genericProductDtoList.add(genericProductDto);

            }

            return genericProductDtoList;

        }

    @Override
    public GenericProductDto DeleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        ResponseEntity<FakeStoreProductDto> response =  restTemplate.exchange(productUrlForDeletingProduct, HttpMethod.DELETE,null, FakeStoreProductDto.class,id);

        FakeStoreProductDto fakeStoreProductDto = response.getBody();
        GenericProductDto genericProductDto = Insertingdata(fakeStoreProductDto);



        return genericProductDto;


    }


}
