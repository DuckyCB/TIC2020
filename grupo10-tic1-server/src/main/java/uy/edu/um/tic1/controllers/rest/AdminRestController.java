package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.AdminController;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.users.AdminUser;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private AdminController adminController;

    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin(){
        return "You are an Admin!";
    }


    @GetMapping("/create/")
    public void createAdminUser(){
        adminController.createAdminUser();

    }

}
