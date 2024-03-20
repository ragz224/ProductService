package com.raghu.productservice.DTOs;

import com.raghu.productservice.Models.Category;
import com.raghu.productservice.Models.Price;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Getter
@Setter
public class GenericProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String title;
    private String price;
    private String description;
    private String image;
    private String category;

}
