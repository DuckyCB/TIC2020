package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.repositories.BrandRepository;

import java.util.Optional;

@Service
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;


    public void save(Brand brand) {
        brandRepository.save(brand);
    }

    public Iterable<Brand> findByName(String name){

        return brandRepository.findByName(name);
    }

    public Brand findById(Integer id){
        Optional<Brand> brand = brandRepository.findById(id);
        if (brand.isPresent()){
            return brand.get();
        }

        return null;
    }
}
