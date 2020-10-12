package uy.edu.um.tic1.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.Stock;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;
import uy.edu.um.tic1.entities.sizecolor.Size;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;
import uy.edu.um.tic1.repositories.StockRepository;

import java.net.Socket;
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

//    public void addStock(Integer product_id, Integer store_id, Integer stock, ColorImpl color, Size size) {
//
//
//        Store store = storeController.findById(store_id);
//        if (store != null){
//            Stock stockObject = store.getStock(product_id, color, size);
//            if (stock != null){
//                stockObject.setStock(stock);
//            }else{
//                stockObject = Stock.builder()
//                        .stock(stock)
//                        .product()
//                        .sizeAndColor(new SizeAndColor(size, color))
//
//            }
//        }
//
//
//    }


}
