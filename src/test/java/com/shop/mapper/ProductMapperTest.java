package com.shop.mapper;

import com.shop.domain.Category;
import com.shop.domain.Product;
import com.shop.domain.dto.ProductDto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockMultipartFile;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductMapperTest {


    private ProductMapper productMapper;
    private Product product;
    private List<Product> products=new ArrayList<>();
    private Category category;
    private List<Category>categories=new ArrayList<>();

    @Before
    public void before(){
        category = new Category(1L, "category_test", "category_desc_test", new ArrayList<>(), new MockMultipartFile("test"
                , "test.png"
                , "image/png",
                "content_test".getBytes()));
        categories.add(category);
        product = new Product(1L,"product_test",new BigDecimal("100"),categories,2.00,true,"product_desc_test",2,"path_test",new MockMultipartFile("test"
                , "test.png"
                , "image/png",
                "content_test".getBytes()));

    }
    @Test
    public void productToProductDto(){
        //Given //When
        ProductDto productDto = productMapper.INSTANCE.productToProductDto(product);
        //Then
        Assert.assertEquals(new Long(1),productDto.getId());
        Assert.assertEquals("product_test",productDto.getName());
        Assert.assertEquals(new BigDecimal("100"),productDto.getPrice());
        Assert.assertEquals(new Long(1),productDto.getCategoryDtos().get(0).getId());
        Assert.assertEquals("category_desc_test",productDto.getCategoryDtos().get(0).getDescription());
        Assert.assertEquals("test",productDto.getCategoryDtos().get(0).getImage().getName());
        Assert.assertEquals(1,productDto.getCategoryDtos().size());
        Assert.assertEquals(2.00,productDto.getShippingWeight(),2);
        Assert.assertEquals(true,productDto.isAvailable());
        Assert.assertEquals("product_desc_test",productDto.getDescription());
        Assert.assertEquals(2,productDto.getQuantity());
        Assert.assertEquals("path_test",productDto.getImagePath());
        Assert.assertEquals("test",productDto.getImage().getName());


    }
}
