package com.shop.mapper;


import com.shop.domain.Category;
import com.shop.domain.Product;
import com.shop.domain.dto.CategoryDto;
import com.shop.domain.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);
    @Mapping(target = "categoryDtos", source = "categories")
    ProductDto productToProductDto(Product product);
    /*@Mapping(target = "categoryDtos", source = "categories")
    Product productDtoToProduct(ProductDto productDto);*/
}
