package com.raghu.productservice.DTOs;

import com.raghu.productservice.Models.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    private String id;
    private String title;
    private double price;
    private String description;
    private String image;
    private String category;

}
