package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.ProductController;
import uy.edu.um.tic1.entities.*;

@RestController
@RequestMapping("/products")
public class ProductRestController {

    @Autowired
    ProductController productController;



    @GetMapping("/")
    @PreAuthorize("hasAuthority('product:read')")
    public Iterable<Product> getAll(@RequestParam(name="id",required = false) Integer id,
                                    @RequestParam(name="name",required = false) String name,
                                    @RequestParam(name="gender",required = false) Character gender,
                                    @RequestParam(name="brand",required = false) String brand_id,
                                    @RequestParam(name="size",required = false) String size,
                                    @RequestParam(name="color",required = false) String color
                                    ){

        return productController.find(id, name, gender, brand_id, size, color);

    }





    @GetMapping("/insert/")
    @PreAuthorize("hasAuthority('product:write')")
    public void testInsert(){
        productController.testProduct1();
    }


}
