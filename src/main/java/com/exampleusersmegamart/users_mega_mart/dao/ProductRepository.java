package com.exampleusersmegamart.users_mega_mart.dao;

import com.exampleusersmegamart.users_mega_mart.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAllByOrderByProductNameAsc();
}
