package uy.edu.um.tic1.requests;


import javafx.scene.image.ImageView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.BrandFilters;
import uy.edu.um.tic1.entitites.BrandDTO;

import java.util.List;

@Service
public class BrandRestController {


    @Autowired
    private StoreApplication storeApplication;
//    @Autowired
//    private RestTemplate restTemplate;

    public List<BrandDTO> getAllBrands(BrandFilters filters) {
        RestTemplate restTemplate = new RestTemplate();

        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                    storeApplication.getPassword()));
//        else {
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
//        }

        ResponseEntity<List<BrandDTO>> response
                = restTemplate.exchange("http://localhost:8080/brands/" + filters.toString(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BrandDTO>>(){});


        return response.getBody();
    }

    public static ImageView getBanner() {

        // TODO: Esta imagen deberia recuperarla de la bdd

        return new ImageView("/uy/edu/um/tic1/images/Banner/teens.jpg");

    }

}
