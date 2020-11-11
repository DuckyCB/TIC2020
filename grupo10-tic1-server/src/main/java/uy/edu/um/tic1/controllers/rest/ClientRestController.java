package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.ClientController;
import uy.edu.um.tic1.entitites.cart.CartDTO;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientRestController {

    @Autowired
    private ClientController clientController;


    @GetMapping("/carts/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public List<CartDTO> getCarts(@RequestHeader("authorization") String auth){
        return clientController.getCarts(auth);
    }

    @GetMapping("/carts/current/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public CartDTO getCurrentCart(@RequestHeader("authorization") String auth){
        return clientController.getCurrentCart(auth);
    }

}
