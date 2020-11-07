package uy.edu.um.tic1.requests;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.entitites.users.AppUserDTO;

@Service
public class UserRestController {

    public AppUserDTO getUser(String user, String password) {

        RestTemplate restTemplate = new RestTemplate();

//        if (storeApplication.getAppUser() != null)
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
//                    storeApplication.getAppUser().getUsername()));

        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, password));

        ResponseEntity<AppUserDTO> response
                = restTemplate.exchange("http://localhost:8080/admin/user/?username=" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AppUserDTO>(){});



        return response.getBody();

    }
}
