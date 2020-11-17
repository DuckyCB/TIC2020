package uy.edu.um.tic1.requests;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.util.List;

@Service
public class ProductRestController {


    @Autowired
    private StoreApplication storeApplication;



    public List<ProductDTO> getProducts(ProductFilters filters) {
        RestTemplate restTemplate = new RestTemplate();
        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));
        else {
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
        }
        ResponseEntity<List<ProductDTO>> response
                = restTemplate.exchange("http://localhost:8080/products/" + filters.toString() ,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDTO>>(){});


        return response.getBody();
    }

}