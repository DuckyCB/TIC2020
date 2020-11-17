package uy.edu.um.tic1.requests;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

@Service
public class UserRestController {

    public AppUserDTO getUser(String user, String password) {

        RestTemplate restTemplate = new RestTemplate();


//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, password));
//        restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(user, password));

        ResponseEntity<AppUserDTO> response
                = restTemplate.exchange("http://localhost:8080/admin/user/?username=" + user,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<AppUserDTO>(){});




        return response.getBody();

    }


    public void registerClient(ClientDTO client) {

        RestTemplate restTemplate = new RestTemplate();

//        if (storeApplication.getAppUser() != null)
//            restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(storeApplication.getAppUser().getUsername(),
//                    storeApplication.getAppUser().getUsername()));

        MultiValueMap<String, String> body = new LinkedMultiValueMap<String, String>();

        body.add("field", "value");

// Note the body object as first parameter!
        HttpEntity<ClientDTO> httpEntity = new HttpEntity<ClientDTO>(client, body);


        ResponseEntity<Void> response
                = restTemplate.exchange("http://localhost:8080/admin/register/client/",
                HttpMethod.PUT,
                httpEntity,
                new ParameterizedTypeReference<Void>(){});

    }

}
