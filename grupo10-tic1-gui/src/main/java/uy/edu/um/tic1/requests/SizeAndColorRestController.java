package uy.edu.um.tic1.requests;

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
import uy.edu.um.tic1.entitites.SizeAndColorDTO;

import java.util.List;

@Service
public class SizeAndColorRestController {


    @Autowired
    private StoreApplication storeApplication;
//    @Autowired
//    private RestTemplate restTemplate;

    public List<SizeAndColorDTO> getSizeAndColor(String size, String color) {
        RestTemplate restTemplate = new RestTemplate();

//        if (storeApplication.getAppUser() != null)
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
//                    storeApplication.getAppUser().getUsername()));

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor("admin1", "admin1"));
        ResponseEntity<List<SizeAndColorDTO>> response
                = restTemplate.exchange("http://localhost:8080/size-color/?" + "size=" + size + "&color=" + color,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<SizeAndColorDTO>>(){});


        return response.getBody();
    }

}
