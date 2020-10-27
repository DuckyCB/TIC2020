package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.repositories.StoreRepository;
import uy.edu.um.tic1.repositories.specifications.StoreQuerySpecification;

import java.util.List;

@Service
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;


    public void save(Store store){
        storeRepository.save(store);
    }



    public List<Store> findAll(Integer id, String address, TelephoneNumber telephoneNumber, Brand brand, Product product){
        return storeRepository.findAll(StoreQuerySpecification.builder()
                .id(id)
                .address(address)
                .telephoneNumber(telephoneNumber)
                .brand(brand)
                .product(product)
                .build()
        );
    }





}
