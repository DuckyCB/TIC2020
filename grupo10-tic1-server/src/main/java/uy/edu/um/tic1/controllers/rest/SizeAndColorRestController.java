package uy.edu.um.tic1.controllers.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uy.edu.um.tic1.controllers.SizeAndColorController;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;

import java.util.List;

@RestController
@RequestMapping("/size-color/")
public class SizeAndColorRestController {



    @Autowired
    private SizeAndColorController sizeAndColorController;

    @GetMapping("/")
    public List<SizeAndColorDTO> getAll(@RequestParam(name="size",required = false) String size,
                                        @RequestParam(name="color",required = false) String color
    ){
        return sizeAndColorController.getAll(size, color);
    }



}
