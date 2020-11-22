package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.util.List;

@RestController
@RequestMapping("/stores")
public class StoreRestController {

    @Autowired
    private StoreController storeController;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('store:read')")
    public List<StoreDTO> getAll(@RequestParam(name="id",required = false) Integer id,
                                 @RequestParam(name="address",required = false) String address,
                                 @RequestParam(name="phone",required = false) String phone
            ){


        return storeController.findAll(id, address, null, null, null);
    }

    @GetMapping("/my-store/")
    @PreAuthorize("hasRole('ROLE_STORE')")
    public StoreDTO getStore(@RequestHeader("authorization") String auth){

        return storeController.getStore(auth);
    }



    @PutMapping("/insert/")
    @PreAuthorize("hasAuthority('store:write')")
    public void insert(@RequestBody Store store){
        storeController.save(store);
    }

    @DeleteMapping("/delete/")
    @PreAuthorize("hasAuthority('store:write')")
    public void delete(@RequestBody Store store){
        storeController.delete(store);
    }


    @GetMapping("/purchases/")
    @PreAuthorize("hasRole('ROLE_STORE')")
    public List<PurchaseDTO> getPurchases(@RequestHeader("authorization") String auth,
                                          @RequestParam(name="delivered",required = true) Boolean delivered){

        return storeController.getPurchases(auth, delivered);
    }

    @GetMapping("/products/")
    @PreAuthorize("hasRole('ROLE_STORE')")
    public List<ProductDTO> getProducts(@RequestHeader("authorization") String auth,
                                        @RequestParam(name="inStock",required = true) Boolean inStock){

        return storeController.getProducts(auth, inStock);
    }

}
