package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.repositories.StockRepository;

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




}
