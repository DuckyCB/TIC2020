package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.entities.products.Product;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductController productController;



    @GetMapping("/")
    @PreAuthorize("hasAuthority('product:read')")
    public List<Product> getAll(@RequestParam(name="id",required = false) Integer id,
                                @RequestParam(name="name",required = false) String name,
                                @RequestParam(name="gender",required = false) Character gender,
                                @RequestParam(name="brand",required = false) Integer brand_id,
                                @RequestParam(name="size",required = false) String size,
                                @RequestParam(name="color",required = false) String color,
                                @RequestParam(name="stock",required = false) Integer stock,
                                @RequestParam(name="from",required = false) Double from,
                                @RequestParam(name="to",required = false) Double to,
                                @RequestParam(name="type",required = false) String type,
                                @RequestParam(name="hasStock",required = false) Boolean hasStock
                                    ){

        if (hasStock == null || hasStock == true){
            return productController.findWithStock(type, id, name, gender, brand_id, size, color, stock, from, to);
        }

        return productController.findAll(type, id, name, gender, brand_id, size, color, from, to);

    }





    @GetMapping("/test_insert/")
    @PreAuthorize("hasAuthority('product:write')")
    public void testInsert(){
        productController.testProduct1();
    }



}
