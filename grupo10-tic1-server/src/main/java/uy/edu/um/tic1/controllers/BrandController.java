package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.BrandUser;
import uy.edu.um.tic1.entities.users.StoreUser;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.repositories.BrandRepository;
import uy.edu.um.tic1.repositories.specifications.BrandQuerySpecification;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BrandController {

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private StoreController storeController;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private ProductController productController;


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

    public List<ProductDTO> getProducts(String auth) {
        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof BrandUser){
            BrandUser brandUser = (BrandUser) userFromHeader;


            return productController.findAll(null, null, null, null, null,
                    brandUser.getBrand().getId(), null, null, null, null);
            }


        return null;
    }
}
