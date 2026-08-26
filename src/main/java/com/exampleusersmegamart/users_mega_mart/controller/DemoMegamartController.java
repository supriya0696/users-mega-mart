package com.exampleusersmegamart.users_mega_mart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoMegamartController {
    @GetMapping("/")
    public String showHome(){
        return "home";
    }

//    @GetMapping("/viewCatalog")
//    public String viewCatalog(){
//        return "view-product-catalog";
//    }

    @GetMapping("/addCatalog")
    public String addCatalog(){
        return "add-product-catalog";
    }

    @GetMapping("/updateCatalog")
    public String updateCatalog(){
        return "update-product-catalog";
    }

}
