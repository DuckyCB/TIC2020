package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.ClientController;
import uy.edu.um.tic1.entities.cart.Cart;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;

import java.util.HashMap;
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
    //@PreAuthorize("hasRole('ROLE_CLIENT')")
    public CartDTO getCurrentCart(@RequestHeader("authorization") String auth){
        return clientController.getCurrentCart(auth);
    }


    @PutMapping("/carts/current/save/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void saveCurrentCart(@RequestHeader("authorization") String auth, @RequestBody Cart currentCart){
        clientController.saveCurrentCart(auth, currentCart);
    }


    @GetMapping("/carts/buy/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public void buyCurrentCart(@RequestHeader("authorization") String auth,
                               @RequestParam(name="toDeliver",required = true) Boolean toDeliver){
        clientController.buyCurrentCart(auth, toDeliver);
    }

    @GetMapping("/carts/stores/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public HashMap<CartItemDTO, List<StoreDTO>> getStores(@RequestHeader("authorization") String auth){
        return clientController.getStores(auth);
    }


    @GetMapping("/purchases/")
    @PreAuthorize("hasRole('ROLE_CLIENT')")
    public List<PurchaseDTO> getPurchases(@RequestHeader("authorization") String auth,
                                          @RequestParam(name="delivered",required = false) Boolean delivered){

        return clientController.getPurchases(auth, delivered);
    }

}
