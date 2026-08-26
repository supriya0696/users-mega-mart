package com.exampleusersmegamart.users_mega_mart.service;

import com.exampleusersmegamart.users_mega_mart.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll();

    Product findById(int theId);

    Product save(Product theProduct);

    void deleteById(int theId);
}
