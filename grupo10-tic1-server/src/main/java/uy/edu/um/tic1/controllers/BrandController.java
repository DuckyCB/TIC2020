package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.repositories.BrandRepository;

@Service
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;


    public void save(Brand brand) {
        brandRepository.save(brand);
    }
}
