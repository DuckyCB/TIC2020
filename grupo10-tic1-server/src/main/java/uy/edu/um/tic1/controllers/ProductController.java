package uy.edu.um.tic1.controllers;

import com.mysql.cj.jdbc.result.UpdatableResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;
import uy.edu.um.tic1.repositories.BrandRepository;
import uy.edu.um.tic1.repositories.product.ProductRepository;
import uy.edu.um.tic1.repositories.product.ProductShirtRepository;
import uy.edu.um.tic1.repositories.product.ProductTrousersRepository;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

@Service
public class ProductController {


    @Autowired
    private ProductRepository<Product> productRepository;
    @Autowired
    private ProductShirtRepository productShirtRepository;
    @Autowired
    private StoreController storeController;
    @Autowired
    private BrandController brandController;
    @Autowired
    private StockController stockController;
    @Autowired
    private ProductTrousersRepository productTrousersRepository;

//    public void testProduct(){
//        List<Size> size = new ArrayList<>();
//        size.add(new Size("M"));
//        size.add(new Size("L"));
//
//
//        Brand brand = Brand.builder()
//                .name("Levi's")
//                .email(new Email("manager", "levis"))
//                .build();
//        brandController.save(brand);
//
//        Set<Brand> brandSet = new LinkedHashSet<>();
//
//        brandSet.add(brand);
//
//
//
//        ByteArrayOutputStream bos = null;
//
//        try {
//            BufferedImage bImage = ImageIO.read(new File("C:\\Users\\Usuario\\git\\tic1\\tic2020\\grupo10-tic1-server\\src\\main\\resources\\black.jpg"));
//            bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] image = bos.toByteArray();
//
//
//        Product product = Trousers.builder()
//                .name("Black Jean")
//                .price(699.99)
//                .size(size)
//                .color("Negro")
//                .brand(brand)
//                .gender('M')
//                .image(image)
//                .build();
//
//        Product product2 = Shirt.builder()
//                .name("Black Jean")
//                .price(699.99)
//                .size(size)
//                .color("Negro")
//                .brand(brand)
//                .gender('M')
//                .image(image)
//                .build();
//
//        productRepository.save(product);
//        productRepository.save(product2);
//
//        Store store = Store.builder()
//                .address("8 de Octubre")
//                .telephoneNumber(new TelephoneNumber(9999))
//                .brands(brandSet)
//                .build();
//
//        storeController.save(store);
//
//        Stock stock = new Stock(new StockId(product.getId(),store.getId()), 10);
//        Stock stock2 = new Stock(new StockId(product2.getId(),store.getId()), 0);
//
//        stockController.save(stock);
//        stockController.save(stock2);
//    }

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

    public List<Product> getByBrand(String name){
        List<Product> products = new ArrayList<>();
        productRepository.findByBrand(name).forEach(p ->{
            products.add(p);
        });
        return products;
    }


    public List<Product> getAllShirts(){
        List<Product> shirts = new ArrayList<>();
        productShirtRepository.findAll().forEach(s -> {
            shirts.add(s);
        });

        return shirts;

    }

    public List<Product> getAllWithStock() {
        List<Product> products = new ArrayList<>();
        productRepository.findAllWithStock().forEach(p -> {
            products.add(p);
        });

        return products;
    }


    public void newProduct(Product product) {
        productRepository.save(product);
    }

    public Product findById(Integer product_id) {
        Optional<Product> product  = productRepository.findById(product_id);
        if (product.isPresent())
            return product.get();
        return null;
    }


    public List<Product> getAllTrouserWithStock(){
        List<Product> products = new ArrayList<>();
        productTrousersRepository.findAllTrousersWithStock().forEach(p -> {
            products.add(p);
        });

        return products;
    }

    public List<Product> getAllShirtsWithStock(){
        List<Product> products = new ArrayList<>();
        productShirtRepository.findAllWithStock().forEach(p -> {
            products.add(p);
        });

        return products;
    }
}
