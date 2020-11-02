package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.StoreDTO;

import java.util.List;

@RestController
@RequestMapping("/store")
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

}
