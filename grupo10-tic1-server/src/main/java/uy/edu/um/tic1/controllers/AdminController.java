package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.users.AdminUser;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entities.users.StoreUser;
import uy.edu.um.tic1.repositories.UserRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class AdminController {

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

}
