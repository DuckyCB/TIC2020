package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.StockId;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.repositories.StockRepository;

import java.util.Optional;

@Service
public class StockController {

    @Autowired
    private ProductController productController;
    @Autowired
    private StoreController storeController;
    @Autowired
    private StockRepository stockRepository;

    public void save(Stock stock) {
        stockRepository.save(stock);
    }

    public void addStock(Integer product_id, Integer store_id, Integer stock) {

        Optional<Stock> stockOptional = stockRepository.findById(new StockId(product_id, store_id));
        Stock new_stock = null;
        if (stockOptional.isPresent()){
            new_stock = stockOptional.get();
            new_stock.setStock(stock);
        }else{
            Product product = productController.findById(product_id);
            Store store = storeController.findById(store_id);

            new_stock = Stock.builder()
                    .id(new StockId(product.getId(), store.getId()))
                    .stock(stock)
                    .build();

        }


        stockRepository.save(new_stock);


    }


}
