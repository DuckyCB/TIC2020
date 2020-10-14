package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.entities.*;



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

    @GetMapping("/color")
    public Iterable<Product> getAllByColor(@RequestParam String color_name){
        return productController.getAllProductsByColor(color_name);
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




    @GetMapping("/test_insert")
    public void testInsert(){

        productController.testProduct();

    }

    @GetMapping("/trousers/stock")
    public List<Product> getAllTrousersWithStock(){

        return productController.getAllTrouserWithStock();

    }

    @GetMapping("/shirt/stock")
    public List<Product> getAllShirtsWithStock(){

        return productController.getAllShirtsWithStock();

    }



    @PostMapping("/insert")
    public void newProduct(@RequestBody Product product){

        productController.newProduct(product);

    }


}
