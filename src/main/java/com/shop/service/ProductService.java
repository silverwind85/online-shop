package com.shop.service;


import com.shop.domain.Product;
import com.shop.domain.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> findAll();
    ProductDto findById(Long id);
}
