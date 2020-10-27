package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.repositories.SizeAndColorRepository;
import uy.edu.um.tic1.repositories.specifications.SizeAndColorSpecification;

import java.util.List;

@Service
public class SizeAndColorController {



    @Autowired
    private SizeAndColorRepository sizeAndColorRepository;


    public void save(SizeAndColor sizeAndColor){
        sizeAndColorRepository.save(sizeAndColor);

    }

    public Iterable<SizeAndColor> findAll(String color, String size){
        return sizeAndColorRepository.findAll(SizeAndColorSpecification.builder()
        .color(color)
        .size(size)
        .build()
        );
    }


}
