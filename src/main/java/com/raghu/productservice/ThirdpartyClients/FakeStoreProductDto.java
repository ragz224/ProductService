package com.raghu.productservice.ThirdpartyClients;

import com.raghu.productservice.Category;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
@Setter
public class FakeStoreProductDto {
    private int id;

    private String description;

    private String category;
    private double price;
    private String title;
    private String image;
}
