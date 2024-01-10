package com.raghu.productservice.Services;

import com.raghu.productservice.ThirdpartyClients.FakeStoreClient;
import com.raghu.productservice.ThirdpartyClients.FakeStoreProductDto;
import com.raghu.productservice.DTOs.GenericProductDto;
import com.raghu.productservice.Exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakestoreproductservice")
public class FakeStoreProductService implements ProductService{
    FakeStoreClient fakeStoreClient;
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

            GenericProductDto genericProductDto = Insertingdata(fakeStoreClient.getProductById(id));
            return genericProductDto;
        }



    public GenericProductDto uploadProduct(GenericProductDto genericProductDto) {

        GenericProductDto genericProductDto1 = Insertingdata(fakeStoreClient.uploadProduct(genericProductDto));
        return genericProductDto1;

    }



    @Override
    public GenericProductDto updateProductById(Long id, GenericProductDto updatedProductDto) {


        GenericProductDto genericProductDto1 =Insertingdata(fakeStoreClient.updateProductById(id,updatedProductDto));

        return genericProductDto1;


    }


    @Override
        public List<GenericProductDto> getAllProducts() {




            List<GenericProductDto> genericProductDtoList = new ArrayList<>();

            for (FakeStoreProductDto fakeStoreProductDto : fakeStoreClient.getAllProducts()) {
                GenericProductDto genericProductDto =Insertingdata(fakeStoreProductDto);

                genericProductDtoList.add(genericProductDto);

            }

            return genericProductDtoList;

        }

    @Override
    public GenericProductDto DeleteProductById(Long id) {

        GenericProductDto genericProductDto = Insertingdata(fakeStoreClient.DeleteProductById(id));
        return genericProductDto;


    }


}
