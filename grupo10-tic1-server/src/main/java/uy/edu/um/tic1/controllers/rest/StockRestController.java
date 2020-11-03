package uy.edu.um.tic1.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uy.edu.um.tic1.controllers.StockController;
import uy.edu.um.tic1.controllers.StoreController;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.repositories.specifications.StoreQuerySpecification;


@RestController
@RequestMapping("/stocks")

public class StockRestController {

    @Autowired
    private StockController stockController;
    @Autowired
    private StoreController storeController;






    @PutMapping("/insert/")
    @PreAuthorize("hasAuthority('stock:write')")
    public void insert(@RequestBody Stock stock, Store store){
        stockController.save(stock);
        storeController.updateStock(store, stock);
    }

    @DeleteMapping("/delete/")
    @PreAuthorize("hasAuthority('store:write')")
    public void delete(@RequestBody Stock stock, Store store){
        storeController.deleteStock(store, stock);
    }






}
