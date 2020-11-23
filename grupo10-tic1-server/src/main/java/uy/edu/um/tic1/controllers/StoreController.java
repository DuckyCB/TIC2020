package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.cart.Purchase;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.users.AppUser;
import uy.edu.um.tic1.entities.users.Client;
import uy.edu.um.tic1.entities.users.StoreUser;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.repositories.ProductRepository;
import uy.edu.um.tic1.repositories.StockRepository;
import uy.edu.um.tic1.repositories.StoreRepository;
import uy.edu.um.tic1.repositories.specifications.StoreQuerySpecification;
import uy.edu.um.tic1.security.user.ApplicationUserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class StoreController {

    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ApplicationUserService applicationUserService;
    @Autowired
    private ProductController productController;


    public StoreUser getStoreUserFromHeader(String auth) {
        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof StoreUser) {
            StoreUser storeUser = (StoreUser) userFromHeader;
            return storeUser;
        }
        return null;
    }

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

    public void addStock(String auth, Stock stock) {

        StoreUser storeUser = getStoreUserFromHeader(auth);

        if(storeUser != null){
            Store store = storeUser.getStore();
//            stockRepository.save(stock);
            store.addStock(stock);

            storeRepository.save(store);
        }

    }

    public void deleteStock(String auth, Stock stock) {
        StoreUser storeUser = getStoreUserFromHeader(auth);

        if(storeUser != null){
            Store store = storeUser.getStore();
            store.deleteStock(stock);
            storeRepository.save(store);
        }
    }



    public void addBrand(String auth, Brand brand) {

        StoreUser storeUser = getStoreUserFromHeader(auth);

        if(storeUser != null){
            Store store = storeUser.getStore();
            store.addBrand(brand);
            storeRepository.save(store);
        }

    }

    public void deleteBrand(String auth, Brand brand) {

        StoreUser storeUser = getStoreUserFromHeader(auth);

        if(storeUser != null){
            Store store = storeUser.getStore();
            store.deleteBrand(brand);
            storeRepository.save(store);
        }



    }

    public List<PurchaseDTO> getPurchases(String auth, Boolean delivered) {
        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof StoreUser){
            StoreUser storeUser = (StoreUser) userFromHeader;

            if(delivered)
                return storeUser.getStore().getPurchaseSet().stream().map(Purchase::toDTO).collect(Collectors.toList());
            else
                return storeUser.getStore().getPurchaseSet().stream().map(Purchase::toDTO).collect(Collectors.toList())
                        .stream().filter(p -> !p.getDelivered()).collect(Collectors.toList());
        }

        return null;
    }

    public List<ProductDTO> getProducts(String auth, Boolean inStock) {

        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof StoreUser){
            StoreUser storeUser = (StoreUser) userFromHeader;

            if(inStock) {
                HashMap<Integer, ProductDTO> productsHash = new LinkedHashMap<>();

                storeUser.getStore().getStockSet().stream().forEach(stock -> {
                            if(stock.getStock() > 0){
                                ProductDTO productDTO = stock.getProduct().toDTO();
                                productsHash.put(productDTO.getId(), productDTO);
                            }

                        });
                return productsHash.values().stream().collect(Collectors.toList());
            }
            else{
                List<ProductDTO> productsList = new ArrayList<>();
                storeUser.getStore().getBrandSet().stream().forEach(brand -> {
                    productController.findAll(null, null, null, null, null, brand.getId(),
                            null, null, null, null).stream().forEach(p ->{
                                productsList.add(p);
                    });
                });
                return productsList;
            }
        }

        return null;

    }


    public List<Store> getStoreByProduct(Product product, Integer stock){

        return storeRepository.findAll(StoreQuerySpecification.builder()
                                                .product(product)
                                                .stock(stock)
                                                .build());

    }

    public StoreDTO getStore(String auth) {
        AppUser userFromHeader = applicationUserService.getUserFromHeader(auth);

        if (userFromHeader != null && userFromHeader instanceof StoreUser) {
            StoreUser storeUser = (StoreUser) userFromHeader;
            return storeUser.getStore().toDTO();
        }
        return null;
    }
}
