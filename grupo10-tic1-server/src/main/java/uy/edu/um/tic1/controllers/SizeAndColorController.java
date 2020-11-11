package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.repositories.SizeAndColorRepository;
import uy.edu.um.tic1.repositories.specifications.SizeAndColorQuerySpecification;

@Service
public class SizeAndColorController {



    @Autowired
    private SizeAndColorRepository sizeAndColorRepository;


    public void save(SizeAndColor sizeAndColor){
        sizeAndColorRepository.save(sizeAndColor);

    }

    public Iterable<SizeAndColor> findAll(String color, String size){
        return sizeAndColorRepository.findAll(SizeAndColorQuerySpecification.builder()
        .color(color)
        .size(size)
        .build()
        );
    }


}
