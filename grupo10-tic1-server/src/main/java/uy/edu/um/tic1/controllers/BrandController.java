package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.repositories.BrandRepository;
import uy.edu.um.tic1.repositories.specifications.BrandQuerySpecification;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;@Autowired
    private StoreController storeController;


    public void save(Brand brand) {
        brandRepository.save(brand);
    }


    public List<BrandDTO> findAll(Integer id, String name, Email email){
        List<Brand> result = brandRepository.findAll(
                BrandQuerySpecification.builder()
                    .id(id)
                    .name(name)
                    .email(email)
                    .build());

        return result.stream().map(Brand::toDTO).collect(Collectors.toList());

    }


    public void delete(Brand brand) {
        brandRepository.delete(brand);
    }

    public void newStore(Brand brand, Store store) {
        storeController.addBrand(store, brand);
    }

    public void deleteStore(Brand brand, Store store) {
        storeController.deleteBrand(store, brand);
    }

    public BrandDTO findById(Integer id) {
        Optional<Brand> optionalBrand = brandRepository.findById(id);
        if(optionalBrand.isPresent()){
            return optionalBrand.get().toDTO();
        }

        return null;
    }
}
