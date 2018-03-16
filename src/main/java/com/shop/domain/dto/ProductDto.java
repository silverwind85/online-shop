package com.shop.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private List<CategoryDto> categoryDtos=new ArrayList<>();
    private double shippingWeight;
    private boolean isAvailable;
    private String description;
    private int quantity;
    private String imagePath;
    private MultipartFile image;

   /* public ProductDto(Long id, String name, BigDecimal price, List<CategoryDto> categoryDtos,
                   double shippingWeight, boolean isAvailable, String description, int quantity) {
        this.name = name;
        this.price = price;
        this.categoryDtos = categoryDtos;
        this.shippingWeight = shippingWeight;
        this.isAvailable = isAvailable;
        this.description = description;
        this.quantity = quantity;*/


}