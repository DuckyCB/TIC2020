package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.repositories.BrandRepository;
import uy.edu.um.tic1.repositories.specifications.BrandQuerySpecification;

import java.util.List;

@Service
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;


    public void save(Brand brand) {
        brandRepository.save(brand);
    }


    public List<Brand> findAll(Integer id, String name, Email email){
        return brandRepository.findAll(BrandQuerySpecification.builder()
                .id(id)
                .name(name)
                .email(email)
                .build()
        );
    }


}
