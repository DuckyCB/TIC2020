package uy.edu.um.tic1.controllers;

import com.mysql.cj.jdbc.result.UpdatableResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;
import uy.edu.um.tic1.entities.sizecolor.ColorImpl;
import uy.edu.um.tic1.entities.sizecolor.Size;
import uy.edu.um.tic1.entities.sizecolor.SizeAndColor;
import uy.edu.um.tic1.repositories.BrandRepository;
import uy.edu.um.tic1.repositories.ColorRepository;
import uy.edu.um.tic1.repositories.SizeAndColorRepository;
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
    @Autowired
    private SizeAndColorRepository sizeAndColorRepository;
    @Autowired
    private ColorRepository colorRepository;


    public void testProduct(){

        Brand brand = brandController.findById(1);

        if (brand == null) {
            brand = Brand.builder()
                    .id(1)
                    .name("Levi's")
                    .email(new Email("manager", "levis"))
                    .build();
            brandController.save(brand);
        }
        Set<Brand> brandSet = new LinkedHashSet<>();
        brandSet.add(brand);



        ColorImpl color = new ColorImpl("negro");
        ColorImpl color2 = new ColorImpl("blanco");
        Size talle1 = new Size("L");
        Size talle2 = new Size("M");


        SizeAndColor sc1 = sizeAndColorRepository.findBySizeAndColor(talle1, color);
        if (sc1 == null){
            sc1 = SizeAndColor.builder()
                    .id(1)
                    .color(color)
                    .size(talle1)
                    .build();
            sizeAndColorRepository.save(sc1);
        }

        SizeAndColor sc2 = sizeAndColorRepository.findBySizeAndColor(talle2, color);
        if (sc2 == null){
            sc2 = SizeAndColor.builder()
                    .id(2)
                    .color(color)
                    .size(talle2)
                    .build();
            sizeAndColorRepository.save(sc2);
        }

        SizeAndColor sc3 = sizeAndColorRepository.findBySizeAndColor(talle2, color2);
        if (sc3 == null){
            sc3 = SizeAndColor.builder()
                    .id(3)
                    .color(color2)
                    .size(talle2)
                    .build();
            sizeAndColorRepository.save(sc3);
        }



        Set<SizeAndColor> sizeAndColor = new LinkedHashSet<>();
        sizeAndColor.add(sc1);
        sizeAndColor.add(sc2);

        Set<SizeAndColor> sizeAndColor2 = new LinkedHashSet<>();
        sizeAndColor2.add(sc1);
        sizeAndColor2.add(sc3);

        ByteArrayOutputStream bos = null;
        try {
            BufferedImage bImage = ImageIO.read(new File("C:\\Users\\Usuario\\git\\tic1\\tic2020\\grupo10-tic1-server\\src\\main\\resources\\black.jpg"));
            bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] image = bos.toByteArray();





        Product product1 = this.findById(1);
        if (product1 == null) {
            product1 = Trousers.builder()
                    .id(1)
                    .name("Black Jean")
                    .price(699.99)
                    .brand(brand)
                    .gender('M')
                    .image(image)
                    .sizeAndColor(sizeAndColor)
                    .build();
            productRepository.save(product1);
        }

        Product product2 = this.findById(2);
        if(product2 == null) {
           product2 = Shirt.builder()
                    .id(2)
                    .name("White Shirt")
                    .price(699.99)
                    .brand(brand)
                    .gender('M')
                    .image(image)
                    .sizeAndColor(sizeAndColor2)
                    .build();
            productRepository.save(product2);
        }

        TelephoneNumber telephoneNumber = new TelephoneNumber(9999);
        Store store = storeController.findByTelephoneNumber(telephoneNumber);

        if (store == null) {
            store = Store.builder()
                    .id(1)
                    .address("8 de Octubre")
                    .telephoneNumber(telephoneNumber)
                    .brandSet(brandSet)
                    .build();

            Set<Stock> stockSet = new LinkedHashSet<>();


            Stock stock1 = Stock.builder()
                    .id(1)
                    .stock(10)
                    .product(product1)
                    .sizeAndColor(sc1)
                    .build();
            Stock stock2 = Stock.builder()
                    .id(2)
                    .stock(10)
                    .product(product1)
                    .sizeAndColor(sc2)
                    .build();

            stockSet.add(stock1);
            stockSet.add(stock2);
            store.setStockSet(stockSet);

            storeController.save(store);
        }




    }

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

    public Iterable<Product> getAllProductsByColor(String color) {

        Optional<ColorImpl> colorOptional = colorRepository.findByColor(color);

        if (colorOptional.isPresent()){

            return productRepository.findAllWithStockByColor(colorOptional.get());

        }

        return null;
    }
}
