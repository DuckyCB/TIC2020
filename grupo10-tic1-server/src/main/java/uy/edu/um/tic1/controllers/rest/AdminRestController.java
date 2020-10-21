package uy.edu.um.tic1.controllers.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminRestController {


    @GetMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String testAdmin(){

        return "You are an Admin!";
    }


}
