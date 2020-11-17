package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.cart.Cart;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.repositories.CartRepository;
import uy.edu.um.tic1.repositories.specifications.CartQuerySpecification;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientController {


    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private CartRepository cartRepository;


    public Client getClientFromHeader(String auth) {
        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof Client) {
            Client client = (Client) userFromHeader;
            return client;
        }
        return null;
    }


    public List<CartDTO> getCarts(String auth) {

        Client client = getClientFromHeader(auth);

        if (client != null){

            List<Cart> carts = cartRepository.findAll(CartQuerySpecification.builder()
                    .client(client).build());

            return carts.stream().map(Cart::toDTO).collect(Collectors.toList());
        }

        return null;

    }

    public CartDTO getCurrentCart(String auth) {

        Client client = getClientFromHeader(auth);

        if (client != null && client.getCurrentCart() != null){
            return client.getCurrentCart().toDTO();
        }

        return null;

    }


    public void saveCurrentCart(String auth, Cart currentCart) {
        Client client = getClientFromHeader(auth);

        if (client != null){
            client.setCurrentCart(currentCart);
            applicationUserService.update(client);
        }
    }
}
