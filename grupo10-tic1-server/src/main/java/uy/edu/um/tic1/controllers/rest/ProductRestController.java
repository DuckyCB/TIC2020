package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.products.Trousers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductController productController;

    @GetMapping("/")
    public List<Product> getAll(){
        return productController.getAllProducts();
    }

    @GetMapping("/stock")
    public List<Product> getAllWithStock(){
        return productController.getAllWithStock();
    }


    @GetMapping("/find/")
    public List<Product> getByName(@RequestParam(value = "name", required = true) String name){
        return productController.getByName(name);
    }

    @GetMapping("/shirt")
    public List<Product> getAllShirts(){
        return productController.getAllShirts();
    }


    @PostMapping("/insert")
    public void newProduct(@RequestBody Product product){

        productController.newProduct(product);

    }

//    @GetMapping("/test_insert")
//    public void testInsert(){
//
//        productController.testProduct();
//
//    }

    @GetMapping("/trousers/stock")
    public List<Product> getAllTrousersWithStock(){

        return productController.getAllTrouserWithStock();

    }

    @GetMapping("/shirt/stock")
    public List<Product> getAllShirtsWithStock(){

        return productController.getAllShirtsWithStock();

    }


}
