package uy.edu.um.tic1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.entities.*;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.repositories.SizeAndColorRepository;
import uy.edu.um.tic1.repositories.product.ProductRepository;
import uy.edu.um.tic1.repositories.product.ProductShirtRepository;
import uy.edu.um.tic1.repositories.product.ProductTrousersRepository;
import uy.edu.um.tic1.repositories.specifications.ProductQuerySpecification;

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



    public void testProduct1(){

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



        String color1 = Product.getColors().get(0);
        String color2 = Product.getColors().get(1);
        String color3 = Product.getColors().get(2);
        String talle1Pantalon = Trousers.getSizes().get(4);
        String talle2Pantalon = Trousers.getSizes().get(5);


        String talle1Camisa = Shirt.getSizes().get(2);
        String talle2Camisa = Shirt.getSizes().get(3);

        SizeAndColor sc1 = sizeAndColorRepository.findBySizeAndColor(talle1Pantalon, color1);
        if (sc1 == null){
            sc1 = SizeAndColor.builder()
                    .id(1)
                    .color(color1)
                    .size(talle1Pantalon)
                    .build();
            sizeAndColorRepository.save(sc1);
        }

        SizeAndColor sc2 = sizeAndColorRepository.findBySizeAndColor(talle2Pantalon, color2);
        if (sc2 == null){
            sc2 = SizeAndColor.builder()
                    .id(2)
                    .color(color2)
                    .size(talle2Pantalon)
                    .build();
            sizeAndColorRepository.save(sc2);
        }

        SizeAndColor sc3 = sizeAndColorRepository.findBySizeAndColor(talle1Camisa, color1);
        if (sc3 == null){
            sc3 = SizeAndColor.builder()
                    .id(3)
                    .color(color3)
                    .size(talle1Camisa)
                    .build();
            sizeAndColorRepository.save(sc3);
        }
        SizeAndColor sc4 = sizeAndColorRepository.findBySizeAndColor(talle2Camisa, color2);
        if (sc4 == null){
            sc4 = SizeAndColor.builder()
                    .id(4)
                    .color(color2)
                    .size(talle2Camisa)
                    .build();
            sizeAndColorRepository.save(sc3);
        }



        Set<SizeAndColor> sizeAndColor = new LinkedHashSet<>();
        sizeAndColor.add(sc1);
        sizeAndColor.add(sc2);

        Set<SizeAndColor> sizeAndColor2 = new LinkedHashSet<>();
        sizeAndColor2.add(sc4);
        sizeAndColor2.add(sc3);

//        ByteArrayOutputStream bos = null;
//        try {
//            BufferedImage bImage = ImageIO.read(new File("C:\\Users\\Usuario\\git\\tic1\\tic2020\\grupo10-tic1-server\\src\\main\\resources\\black.jpg"));
//            bos = new ByteArrayOutputStream();
//            ImageIO.write(bImage, "jpg", bos );
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        byte[] image = bos.toByteArray();






        Product product1 = this.findById(1);

        if (product1 == null) {
            product1 = Trousers.builder()
                    .id(1)
                    .name("Black Jean")
                    .price(699.99)
                    .brand(brand)
                    .gender('M')
                    .image(null)
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
                    .image(null)
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



    public List<Product> find(Integer id, String name, Character gender,
                                        String brand_id, String size, String color){

        Brand brand = null;
        if (brand_id != null)
            brand = brandController.findById(Integer.valueOf(brand_id));

//        if (color != null){
//            return productRepository.findAll(ProductQuerySpecification.productColor(color));
//        }

        return productRepository.findAll(ProductQuerySpecification.builder()
                .id(id)
                .name(name)
                .gender(gender)
                .brand(null)
                .size(size)
                .color(color)
                .build()
        );


    }

    public Product findById(Integer id){
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()){
            return productOptional.get();
        }

        return null;
    }







    public List<Product> getAllShirts(){
        List<Product> shirts = new ArrayList<>();
        productShirtRepository.findAll().forEach(s -> {
            shirts.add(s);
        });

        return shirts;

    }












}
