package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.entities.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductController productController;

    @GetMapping("/")
    public List<Product> getAll(){
        return productController.getAllProducts();
    }

    @GetMapping("/find/")
    public List<Product> getByName(@RequestParam(value = "name", required = true) String name){
        return productController.getByName(name);
    }

}
