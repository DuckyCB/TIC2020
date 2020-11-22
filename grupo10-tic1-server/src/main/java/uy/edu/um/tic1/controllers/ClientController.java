package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.cart.Cart;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.repositories.CartRepository;
import uy.edu.um.tic1.repositories.specifications.CartQuerySpecification;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientController {


    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private StoreController storeController;


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

    public void buyCurrentCart(String auth, Boolean toDeliver) {
        Client client = getClientFromHeader(auth);
        Cart cart = null;
        Store store = null;
        if (client != null){

            cart = client.getCurrentCart();
            List<Store> stores = null;
            List<CartItem> items = cart.getItems().stream().collect(Collectors.toList());
            client.setCurrentCart(Cart.builder().items(new LinkedHashSet<>()).build());
            applicationUserService.update(client);
            for (CartItem cartItem : items){
                if (cartItem.getStore() == null){
                    stores = storeController.getStoreByProduct(cartItem.getProduct(), cartItem.getQuantity());
                    if(!stores.isEmpty()){
                        store = stores.get(0);
                        cartItem.setStore(store);
                    }else{

                        throw new RuntimeException("Not enough Stock");
                    }
                }
            }

            client.buyCart(cart, toDeliver);
            applicationUserService.update(client);
            cart.getItems().stream().forEach(i ->{
                storeController.save(i.getStore());
            });

        }
    }

    public HashMap<CartItemDTO, List<StoreDTO>> getStores(String auth) {
        Client client = getClientFromHeader(auth);

        if (client != null && client.getCurrentCart() != null){
            HashMap<CartItemDTO, List<StoreDTO>> map = new LinkedHashMap<>();

            for (CartItem cartItem : client.getCurrentCart().getItems()){
                map.put(cartItem.toDTO(), storeController.getStoreByProduct(cartItem.getProduct(), cartItem.getQuantity()).stream().map(Store::toDTO).collect(Collectors.toList()));
            }

            return map;

        }

        return null;

    }






}
