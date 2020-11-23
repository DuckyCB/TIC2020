package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.AdminController;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.users.*;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminController adminController;

    @Autowired
    private ApplicationUserService applicationUserService;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin(@RequestHeader("authorization") String auth){

        applicationUserService.getAppUser("levis");

        return "You are an Admin!";
    }


    @GetMapping("/create/")
    public void createAdminUser(){
        adminController.createAdminUser();

    }

    @GetMapping("/user/")
    public AppUserDTO getUser(@RequestParam(name="username",required = true) String username) {
        return adminController.getUser(username);

    }

    @PutMapping("/register/client/")
    public void registerClient(@RequestBody Client newClient){
        adminController.registerClient(newClient);
    }

    @PutMapping("/register/brand/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void registerBrand(@RequestBody BrandUser newBrand){
        adminController.registerBrand(newBrand);
    }

    @PutMapping("/register/store/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void registerStore(@RequestBody StoreUser newStore){

        adminController.registerStore(newStore);
    }


    @GetMapping("/check-username/")
    public Boolean checkUsername(@RequestParam(name="username",required = true) String username){
        return adminController.checkAvailableUsername(username);
    }

}
