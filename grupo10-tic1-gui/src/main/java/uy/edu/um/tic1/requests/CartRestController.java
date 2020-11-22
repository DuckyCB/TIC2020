package uy.edu.um.tic1.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import java.util.List;

@Service
public class CartRestController {


    @Autowired
    private StoreApplication storeApplication;


    public List<CartDTO> getCarts() {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));

        ResponseEntity<List<CartDTO>> response
                = restTemplate.exchange("http://localhost:8080/client/carts/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartDTO>>(){});

        return response.getBody();
    }

    public CartDTO getCurrentCart() {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));

        ResponseEntity<CartDTO> response
                = restTemplate.exchange("http://localhost:8080/client/carts/current/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<CartDTO>(){});

        if (response != null){
            return response.getBody();
        }

        return null;
    }

    public void saveCurrentCart(CartDTO currentCart) {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();
        body.add("field", "value");
        HttpEntity<CartDTO> httpEntity = new HttpEntity<CartDTO>(currentCart, body);


        ResponseEntity<CartDTO> response
                = restTemplate.exchange("http://localhost:8080/client/carts/current/save/",
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<>(){});


    }

    public void buyCurrentCart(Boolean toDeliver) {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));

        ResponseEntity<Void> response
                = restTemplate.exchange("http://localhost:8080/client/carts/buy/?toDeliver=" + toDeliver.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Void>(){});

    }


    public List<CartDTO> getAllCarts() {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));

        ResponseEntity<List<CartDTO>> response
                = restTemplate.exchange("http://localhost:8080/client/carts/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CartDTO>>(){});

        if (response != null){
            return response.getBody();
        }

        return null;
    }




}
