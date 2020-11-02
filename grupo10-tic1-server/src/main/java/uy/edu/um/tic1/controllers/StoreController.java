package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.repositories.StoreRepository;
import uy.edu.um.tic1.repositories.specifications.StoreQuerySpecification;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;


    public void save(Store store){
        storeRepository.save(store);
    }



    public List<StoreDTO> findAll(Integer id, String address, TelephoneNumber telephoneNumber, Brand brand, Product product){
        List<Store> result = storeRepository.findAll(StoreQuerySpecification.builder()
                .id(id)
                .address(address)
                .telephoneNumber(telephoneNumber)
                .brand(brand)
                .product(product)
                .build());
        return result.stream().map(Store::toDTO).collect(Collectors.toList());

    }


    public void delete(Store store) {
        storeRepository.delete(store);
    }

    public void updateStock(Store store, Stock stock) {
        store.updateStock(stock);
        storeRepository.save(store);
    }

    public void deleteStock(Store store, Stock stock) {
        store.deleteStock(stock);
        storeRepository.save(store);
    }

    public void addBrand(Store store, Brand brand) {
        store.addBrand(brand);
        storeRepository.save(store);
    }

    public void deleteBrand(Store store, Brand brand) {
        store.deleteBrand(brand);
        storeRepository.save(store);
    }
}
