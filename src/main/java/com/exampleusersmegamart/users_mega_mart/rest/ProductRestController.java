package com.exampleusersmegamart.users_mega_mart.rest;


import com.exampleusersmegamart.users_mega_mart.entity.Product;
import com.exampleusersmegamart.users_mega_mart.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductRestController {
    private ProductService productService;

    public ProductRestController(ProductService theProductService){
        productService=theProductService;
    }

    @GetMapping("/product")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping("/product/{productId}")
    public Product getProduct(@PathVariable("productId") int employeeId) {
        Product theProduct = productService.findById(employeeId);
        if (theProduct == null) {
            throw new RuntimeException("Employee id not found - " + employeeId);
        }
        return theProduct;
    }

    @PostMapping("/product")
    public Product addProduct(@RequestBody Product theProduct) {
        theProduct.setId(0);
        Product dbProduct = productService.save(theProduct);
        return dbProduct;
    }

    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product theProduct) {

        Product dbProduct = productService.save(theProduct);

        return dbProduct;
    }
    @DeleteMapping("/product/{productId}")
    public String delete(@PathVariable int productId) {
        Product tempProduct = productService.findById(productId);
        if (tempProduct == null) {
            throw new RuntimeException("Product id not found - " + productId);
        }
        productService.deleteById(productId);
        return "Deleted product id - " + productId;
    }
}
