package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.repositories.SizeAndColorRepository;
import uy.edu.um.tic1.repositories.specifications.SizeAndColorQuerySpecification;

import java.util.ArrayList;
import java.util.List;

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


    public List<SizeAndColorDTO> getAll(String size, String color){
        Iterable<SizeAndColor> iterable = this.findAll(color, size);
        List<SizeAndColorDTO> result = new ArrayList<>();
        iterable.iterator().forEachRemaining(sizeAndColor -> {
            result.add(sizeAndColor.toDTO());
        });

        return result;
    }



}
