package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.repositories.StoreRepository;

import java.util.Optional;

@Service
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;


    public void save(Store store){
        storeRepository.save(store);
    }

    public Store findById(Integer store_id){
        Optional<Store> store  = storeRepository.findById(store_id);
        if (store.isPresent())
            return store.get();
        return null;
    }

}
