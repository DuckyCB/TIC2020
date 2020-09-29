package uy.edu.um.tic1.product;

import javafx.scene.image.ImageView;

import java.util.Stack;

public class Products {

    private static Stack<Products> products = new Stack<>();
    private static String productsCategory;

    private ImageView image;
    private String name;
    private String brand;
    private String price;

    public Products(ImageView image, String name, String brand, String price) {
        this.image = image;
        this.name = name;
        this.brand = brand;
        this.price = price;
    }

    public static Stack<Products> getProducts() {
        return products;
    }
    public static void setProducts(Stack<Products> products) {
        Products.products = products;
    }

    public ImageView getImage() {
        return image;
    }
    public void setImage(ImageView image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }

    public static String getProductsCategory() {
        return productsCategory;
    }
    public static void setProductsCategory(String productsCategory) {
        Products.productsCategory = productsCategory;
    }

}
