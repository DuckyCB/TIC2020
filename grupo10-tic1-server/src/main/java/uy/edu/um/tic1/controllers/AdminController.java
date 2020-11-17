package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.users.*;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.repositories.UserRepository;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AdminController {

    @Autowired
    private ApplicationUserService applicationUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;


    public void createAdminUser(){


        Optional<AppUser> optionalAppUser1 = userRepository.findByUsername("admin1");
        if (!optionalAppUser1.isPresent()){
            AppUser user = AdminUser.builder()
                    .username("admin1")
                    .password(passwordEncoder.encode("admin1"))
                    .isAccountNonExpired(true)
                    .isAccountNonLocked(true)
                    .isCredentialsNonExpired(true)
                    .isEnabled(true)
                    .build();

            userRepository.save(user);
        }



    }

    public AppUserDTO getUser(String username){
        Optional<AppUser> optionalAppUser = userRepository.findByUsername(username);

        if (optionalAppUser.isPresent()){
            return optionalAppUser.get().toDTO();
        }
        return null;

    }

    public void registerClient(Client newClient) {

        applicationUserService.save(newClient);
    }

    public void registerBrand(BrandUser newBrand) {
        applicationUserService.save(newBrand);
    }

    public void registerStore(StoreUser newStore) {
        applicationUserService.save(newStore);
    }
}
