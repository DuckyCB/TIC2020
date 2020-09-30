package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.controllers.rest.ProductRestController;
import uy.edu.um.tic1.entities.Product;
import uy.edu.um.tic1.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductController {


    private ProductRepository productRepository;


    public List<Product> getAllProducts(){

        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(p -> {
            products.add(p);
        });

        return products;
    }



    public List<Product> getByName(String name){
        List<Product> products = new ArrayList<>();
        productRepository.findByName(name).forEach(p ->{
            products.add(p);
        });
        return products;
    }

}
