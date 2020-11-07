package uy.edu.um.tic1.requests;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;

import java.util.List;

@Service
public class RequestUser {

    public AppUserDTO getUser(String user, String password) {

        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, password));

        ResponseEntity<AppUserDTO> response
                = restTemplate.exchange("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AppUserDTO>(){});



        return response.getBody();

    }
}
