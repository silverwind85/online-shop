package com.shop.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;
    private String name;
    private BigDecimal price;
    @ManyToMany(cascade = CascadeType.ALL,mappedBy = "products")
    private List<Category> categories = new ArrayList<>();
    private double shippingWeight;
    private boolean isAvailable;
    private String description;
    private int quantity;
    private String imagePath;
    @Transient
    private MultipartFile image;

   /* public Product(String name, BigDecimal price, List<Category> categories,
                   double shippingWeight, boolean isAvailable, String description, int quantity){
        this.name = name;
        this.price = price;
        this.categories = categories;
        this.shippingWeight = shippingWeight;
        this.isAvailable = isAvailable;
        this.description = description;
        this.quantity = quantity;


    }
    public Product(String name, BigDecimal price, double shippingWeight){
        this.name = name;
        this.price = price;
        this.shippingWeight=shippingWeight;
    }*/


}
