package uy.edu.um.tic1.requests;

import org.springframework.beans.factory.annotation.Autowired;
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
import uy.edu.um.tic1.entities.BrandFilters;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.entitites.users.StoreUserDTO;

import java.util.List;

@Service
public class StoreRestController {


    @Autowired
    private StoreApplication storeApplication;
//    @Autowired
//    private RestTemplate restTemplate;

    public StoreDTO getStore() {
        RestTemplate restTemplate = new RestTemplate();


        if (storeApplication.getAppUser() != null && storeApplication.getAppUser() instanceof StoreUserDTO)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));
//        else {
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
//        }

        ResponseEntity<StoreDTO> response
                = restTemplate.exchange("http://localhost:8080/stores/my-store/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<StoreDTO>(){});


        return response.getBody();

    }

    public void save(StoreDTO store) {
        RestTemplate restTemplate = new RestTemplate();



        if (storeApplication.getAppUser() != null && storeApplication.getAppUser() instanceof StoreUserDTO) {
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));


        }
//        else {
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
//        }
        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("field", "value");
        HttpEntity<StoreDTO> httpEntity = new HttpEntity<StoreDTO>(store, body);

        ResponseEntity<Void> response
                = restTemplate.exchange("http://localhost:8080/stores/insert/",
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<Void>(){});


    }


    public List<ProductDTO> getStoreProducts(Boolean inStock) {
        RestTemplate restTemplate = new RestTemplate();


        if (storeApplication.getAppUser() != null && storeApplication.getAppUser() instanceof StoreUserDTO)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));
//        else {
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
//        }

        ResponseEntity<List<ProductDTO>> response
                = restTemplate.exchange("http://localhost:8080/stores/products/?inStock=" + inStock.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ProductDTO>>(){});


        return response.getBody();

    }


    public List<PurchaseDTO> getPurchases(Boolean delivered) {
        RestTemplate restTemplate = new RestTemplate();


        if (storeApplication.getAppUser() != null && storeApplication.getAppUser() instanceof StoreUserDTO)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));
//        else {
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
//        }

        ResponseEntity<List<PurchaseDTO>> response
                = restTemplate.exchange("http://localhost:8080/stores/purchases/?delivered=" + delivered.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<PurchaseDTO>>(){});


        return response.getBody();

    }

}
