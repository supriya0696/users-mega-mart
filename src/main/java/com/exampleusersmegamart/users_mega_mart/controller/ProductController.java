package com.exampleusersmegamart.users_mega_mart.controller;

import com.exampleusersmegamart.users_mega_mart.entity.Product;
import com.exampleusersmegamart.users_mega_mart.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class ProductController {
    private ProductService productService;
    public ProductController(ProductService theProductService){
        productService = theProductService;
    }

    @GetMapping("/viewCatalog")
    public String listProduct(Model theModel){
        List<Product> theProducts = productService.findAll();
        theModel.addAttribute( "theProducts" ,theProducts);
        return "/view-product-catalog";
    }

    @GetMapping("/showProductCatalogForm")
    public String showProductCatalogForm(Model theModel){
        Product theProduct = new Product();
        ;        theModel.addAttribute( "product" ,theProduct);
        return "/add-product-catalog";
    }

    @PostMapping("/save")
    public String saveProduct(@ModelAttribute("product")  Product theProduct) {
        productService.save(theProduct);
//        return "redirect:/product/list";
        return "redirect:/viewCatalog";
    }

    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("productId") int theId, Model theModel)
    {
        //get the product dfrom the servce
        Product theProduct =productService.findById(theId);
        theModel.addAttribute("product", theProduct);
        return "/add-product-catalog";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("productId") int theId, Model theModel){
//        Product theProduct =productService.findById(theId);
//        theModel.addAttribute("product", theProduct);
        productService.deleteById(theId);
        return "redirect:/viewCatalog";
    }

}
