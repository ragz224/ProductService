package com.raghu.productservice.DTOs;

import com.raghu.productservice.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GenericProductDto {
    private int id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;
}
