package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.BrandController;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.util.List;

@RestController
@RequestMapping("/brands")
public class BrandRestController {


    @Autowired
    private BrandController brandController;


    @GetMapping("/")
    public List<BrandDTO> getAll(@RequestParam(name="id",required = false) Integer id,
                                 @RequestParam(name="name",required = false) String name
    ){
        return brandController.findAll(id, name, null);
    }



    @PutMapping("/insert/")
    @PreAuthorize("hasAuthority('brand:write')")
    public void insert(@RequestBody Brand brand){
        brandController.save(brand);
    }

    @DeleteMapping("/delete/")
    @PreAuthorize("hasAuthority('store:write')")
    public void delete(@RequestBody Brand brand){
        brandController.delete(brand);
    }

//    @PutMapping("/new-store/")
//    @PreAuthorize("hasAuthority('brand:write, store:wrte')")
//    public void newStore(@RequestBody Brand brand, Store store){
//
//        brandController.newStore(brand, store);
//    }
//
//    @DeleteMapping("/delete-store/")
//    @PreAuthorize("hasAuthority('brand:write, store:wrte')")
//    public void deleteStore(@RequestBody Brand brand, Store store){
//        brandController.deleteStore(brand, store);
//    }

    @GetMapping("/products/")
    //@PreAuthorize("hasRole('ROLE_BRAND')")
    public List<ProductDTO> getProducts(@RequestHeader("authorization") String auth){
        return brandController.getProducts(auth);
    }


}
