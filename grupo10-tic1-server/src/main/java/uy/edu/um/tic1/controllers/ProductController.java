package uy.edu.um.tic1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.cart.CartItem;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.repositories.StockRepository;
import uy.edu.um.tic1.repositories.ProductRepository;
import uy.edu.um.tic1.repositories.specifications.ProductQuerySpecification;
import uy.edu.um.tic1.repositories.specifications.StockQuerySpecification;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductController {


    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StoreController storeController;
    @Autowired
    private BrandController brandController;
    @Autowired
    private StockController stockController;
    @Autowired
    private SizeAndColorController sizeAndColorController;




    public void testProduct1(){






    }

    public void testInsert(){


    }

    public void save(Product product){
        productRepository.save(product);
    }

    public List<ProductDTO> findWithStock(String type, Integer subtype, Integer id, String name, Character gender,
                                          Integer brand_id, String size, String color, Integer stock,
                                          Double from, Double to, Integer order){


        List<Stock> stocks = stockRepository.findAll(StockQuerySpecification.builder()
                        .clothType(type)
                        .clothSubtype(subtype)
                        .product_id(id)
                        .name(name)
                        .gender(gender)
                        .brand_id(brand_id)
                        .size(size)
                        .color(color)
                        .priceFrom(from)
                        .priceTo(to)
                        .desiredStock(stock)
                        .order(order)
                        .build());

        return stocks.stream().map(Stock::getProduct).map(Product::toDTO).collect(Collectors.toList());

    }


    public List<ProductDTO> findAll(String type, Integer subtype, Integer id, String name, Character gender,
                              Integer brand_id, String size, String color,
                              Double from, Double to){


        List<Product> result = productRepository.findAll(ProductQuerySpecification.builder()
                .clothType(type)
                .clothSubtype(subtype)
                .id(id)
                .name(name)
                .gender(gender)
                .brand_id(brand_id)
                .size(size)
                .color(color)
                .priceFrom(from)
                .priceTo(to)
                .build()
        );

        return result.stream().map(Product::toDTO).collect(Collectors.toList());
    }

    public ProductDTO findById(Integer id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get().toDTO();
        }

        return null;
    }


    public void delete(Product product) {
        productRepository.delete(product);
    }


//    public List<ProductDTO> findStoreWithStock(CartItem cartItem, Integer stock){
//
//
//        Product product = cartItem.getProduct();
//
//        List<Stock> stocks = stockRepository.findAll(StockQuerySpecification.builder()
//                .product_id(cartItem.getProduct().getId())
//                .size(cartItem.getSizeAndColor().getSize())
//                .color(cartItem.getSizeAndColor().getColor())
//                .desiredStock(stock)
//                .build());
//
//        return stocks.stream().map(S)
}
