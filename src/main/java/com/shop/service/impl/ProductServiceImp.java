package com.shop.service.impl;

import com.shop.domain.Product;
import com.shop.domain.dto.ProductDto;
import com.shop.mapper.ProductMapper;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductServiceImp implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    private ProductMapper mapperProduct;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    public ProductDto findById(Long id){
        Product product = productRepository.findById(id);
        if(product==null){
            try {
                throw new ProductNotFoundException();
            } catch (ProductNotFoundException e) {
                e.printStackTrace();
            }
        }
        ProductDto productDto =mapperProduct.INSTANCE.productToProductDto(product);
       return productDto;
    }

}
