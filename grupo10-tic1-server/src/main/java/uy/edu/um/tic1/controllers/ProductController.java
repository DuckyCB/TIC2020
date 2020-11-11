package uy.edu.um.tic1.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.*;
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


//        List<BrandDTO> brandList = brandController.findAll(null, "Levi's", null);
//
//        Brand brand = null;
//        if(!brandList.isEmpty())
//            brand = Brand.builder()
//                    .id(brandList.get(0).getId())
//                    .email(Email.builder().domain(brandList.get(0).getEmail().getDomain()).user(brandList.get(0).getEmail().getUser()).build())
//                    .name(brandList.get(0).getName())
//                    .build();
//        if(brand == null){
//            brand = Brand.builder()
//                    .name("Levi's")
//                    .email(new Email("manager", "levis"))
//                    .build();
//            brandController.save(brand);
//        }
//
//        Set<Brand> brandSet = new LinkedHashSet<>();
//        brandSet.add(brand);
//
//        String color1 = Product.getColors().get(0);
//        String color2 = Product.getColors().get(1);
//        String color3 = Product.getColors().get(2);
//        String talle1Pantalon = Trousers.getSizes().get(4);
//        String talle2Pantalon = Trousers.getSizes().get(5);
//        String talle1Camisa = Shirt.getSizes().get(2);
//        String talle2Camisa = Shirt.getSizes().get(3);
//
//        Iterator<SizeAndColor> sizeAndColorIterator = sizeAndColorController.findAll(color1, talle1Pantalon).iterator();
//        SizeAndColor sc1 = null;
//        if(sizeAndColorIterator.hasNext()) {
//            sc1 = sizeAndColorIterator.next();
//        }
//        if (sc1 == null){
//            sc1 = SizeAndColor.builder()
//                    .color(color1)
//                    .size(talle1Pantalon)
//                    .build();
//            sizeAndColorController.save(sc1);
//        }
//
//        sizeAndColorIterator = sizeAndColorController.findAll(color2, talle2Pantalon).iterator();
//        SizeAndColor sc2 = null;
//        if (sizeAndColorIterator.hasNext()){
//            sc2 = sizeAndColorIterator.next();
//        }
//        if (sc2 == null){
//            sc2 = SizeAndColor.builder()
//                    .color(color2)
//                    .size(talle2Pantalon)
//                    .build();
//            sizeAndColorController.save(sc2);
//        }
//
//        sizeAndColorIterator = sizeAndColorController.findAll(color1, talle1Camisa).iterator();
//        SizeAndColor sc3 = null;
//        if (sizeAndColorIterator.hasNext()){
//            sc3 = sizeAndColorIterator.next();
//        }
//        if (sc3 == null){
//            sc3 = SizeAndColor.builder()
//                    .color(color1)
//                    .size(talle1Camisa)
//                    .build();
//            sizeAndColorController.save(sc2);
//        }
//
//        sizeAndColorIterator = sizeAndColorController.findAll(color2, talle2Camisa).iterator();
//        SizeAndColor sc4 = null;
//        if (sizeAndColorIterator.hasNext()){
//            sc4 = sizeAndColorIterator.next();
//        }
//        if (sc4 == null){
//            sc4 = SizeAndColor.builder()
//                    .color(color1)
//                    .size(talle1Camisa)
//                    .build();
//            sizeAndColorController.save(sc2);
//        }
//
//
//
//        Set<SizeAndColor> sizeAndColor = new LinkedHashSet<>();
//        sizeAndColor.add(sc1);
//        sizeAndColor.add(sc2);
//
//        Set<SizeAndColor> sizeAndColor2 = new LinkedHashSet<>();
//        sizeAndColor2.add(sc4);
//        sizeAndColor2.add(sc3);
//




        ByteArrayOutputStream bos = null;
        try {
            BufferedImage bImage = ImageIO.read(new File("C:\\Users\\Usuario\\git\\tic1\\tic2020\\grupo10-tic1-server\\src\\main\\resources\\blue.jpg"));
            bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos );

        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] image = bos.toByteArray();

        Optional<Product> optionalProduct = productRepository.findById(44);

        if (optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setImage(image);

            productRepository.save(product);
        }



//
//
//        List<ProductDTO> productsList = this.findAll(null, null, "Black Jean",
//                null, null, null, null, null, null);
//
//
//        Product product1 = null;
//
//        if(!productsList.isEmpty())
//            product1 = productsList.get(0);
//
//        if (product1 == null) {
//            product1 = Trousers.builder()
//                    .name("Black Jean")
//                    .price(699.99)
//                    .brand(brand)
//                    .gender('M')
//                    .image(null)
//                    .sizeAndColor(sizeAndColor)
//                    .build();
//            productRepository.save(product1);
//        }
//
//        productsList = this.findAll(null, null, "White Shirt",
//                null, null, null, null, null, null);
//
//        Product product2 = null;
//
//        if(!productsList.isEmpty())
//            product2 = productsList.get(0);
//
//        if(product2 == null) {
//            product2 = Shirt.builder()
//                    .name("White Shirt")
//                    .price(699.99)
//                    .brand(brand)
//                    .gender('M')
//                    .image(null)
//                    .sizeAndColor(sizeAndColor2)
//                    .build();
//            productRepository.save(product2);
//        }
//
//
//        TelephoneNumber telephoneNumber = new TelephoneNumber(9999);
//
//
//        List<Store> storeList = storeController.findAll(null, "8 de Octubre 2203", null,
//                null, null);
//        Store store = null;
//        if(!storeList.isEmpty())
//            store = storeList.get(0);
//
//
//        if (store == null) {
//            store = Store.builder()
//                    .address("8 de Octubre 2203")
//                    .telephoneNumber(telephoneNumber)
//                    .brandSet(brandSet)
//                    .build();
//
//            Set<Stock> stockSet = new LinkedHashSet<>();
//
//            Stock stock1 = Stock.builder()
//                    .stock(10)
//                    .product(product1)
//                    .sizeAndColor(sc1)
//                    .build();
//            Stock stock2 = Stock.builder()
//                    .stock(10)
//                    .product(product1)
//                    .sizeAndColor(sc2)
//                    .build();
//
//            stockSet.add(stock1);
//            stockSet.add(stock2);
//            store.setStockSet(stockSet);
//
//            storeController.save(store);
//        }




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
}
