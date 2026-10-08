package com.exampleusersmegamart.users_mega_mart.service;

import com.exampleusersmegamart.users_mega_mart.dao.ProductRepository;
import com.exampleusersmegamart.users_mega_mart.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository theProductRepository ){
        productRepository=theProductRepository;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAllByDeletedFalseOrderByProductNameAsc();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findByIdAndDeletedFalse(theId);
        Product theProduct = null;
        if (result.isPresent()) {
            theProduct = result.get();
        }
        else {
            throw new RuntimeException("Did not find product id - " + theId);
        }
        return theProduct;
    }

    @Override
    public Product save(Product theProduct) {
        return productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        Product theProduct = productRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("Did not find product id - " + theId));
        theProduct.setDeleted(true);
        productRepository.save(theProduct);
    }
}


