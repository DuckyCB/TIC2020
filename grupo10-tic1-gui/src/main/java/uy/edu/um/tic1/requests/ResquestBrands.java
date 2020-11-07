package uy.edu.um.tic1.Requests;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jboss.jandex.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entitites.BrandDTO;

import java.util.List;

@Service
public class ResquestBrands {


    @Autowired
    private StoreApplication storeApplication;


    public List<BrandDTO> getAllBrands(String filters) {

        RestTemplate restTemplate = new RestTemplate();

        if (storeApplication.getAppUser() != null)
            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
                storeApplication.getAppUser().getUsername()));

        ResponseEntity<List<BrandDTO>> response
                = restTemplate.exchange("http://localhost:8080/brands/?" + filters,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BrandDTO>>(){});


        return response.getBody();
    }

}
