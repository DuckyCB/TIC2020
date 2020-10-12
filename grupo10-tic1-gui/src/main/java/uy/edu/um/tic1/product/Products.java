package uy.edu.um.tic1.product;

import javafx.scene.image.ImageView;
import uy.edu.um.tic1.entity.ColorRGB;

import java.util.Stack;

public class Products {

    private String image;
    private String name;
    private String brand;
    private Float price;
    private ColorRGB[] colors;
    private String[] sizes;

    public Products(String image, String name, String brand, Float price, String[] sizes, ColorRGB[] colors) {
        this.image = image;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.colors = colors;
        this.sizes = sizes;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
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

    public Float getPrice() {
        return price;
    }
    public void setPrice(Float price) {
        this.price = price;
    }

    public ColorRGB[] getColors() {
        return colors;
    }
    public void setColors(ColorRGB[] colors) {
        this.colors = colors;
    }

    public String[] getSizes() {
        return sizes;
    }
    public void setSizes(String[] sizes) {
        this.sizes = sizes;
    }
}
