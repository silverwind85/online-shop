package com.shop.controller;


import com.shop.domain.Product;
import com.shop.domain.dto.ProductDto;
import com.shop.domain.dto.UserDto;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import com.shop.service.UserService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/products")
    public String getProducts(Model model) {

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("classActiveProducts", "active");
        return "products";

    }

    @GetMapping("/products/{id}")
    public String getProduct(@PathVariable("id") Long id, Model model, Principal principal)  {

        if (principal != null) {
            String username = principal.getName();
            UserDto userDto = userService.findByUsername(username);
            model.addAttribute("user", userDto);
        }
        ProductDto productDto = null;
        try {

            productDto = productService.findById(id);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*Product productDto = productRepository.findById(id);*/
        model.addAttribute("product", productDto);
        return "product";
    }

}
