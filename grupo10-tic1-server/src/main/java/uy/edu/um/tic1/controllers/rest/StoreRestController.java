package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;

@RestController
@RequestMapping("/store")
public class StoreRestController {

    @Autowired
    StoreController storeController;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('store:read')")
    public Iterable<Store> getAll(@RequestParam(name="id",required = false) Integer id,
                                  @RequestParam(name="address",required = false) String address,
                                  @RequestParam(name="phone",required = false) String phone
            ){


        return storeController.findAll(id, address, null, null, null);
    }

}
