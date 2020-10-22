package uy.edu.um.tic1.product;

import uy.edu.um.tic1.entity.ColorRGB;

public class Product {

    private String image;
    private String name;
    private String brand;
    private Float price;
    private String color;
    private String size;

    public Product(String image, String name, String brand, Float price, String color, String size) {
        this.image = image;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.size = size;
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

    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }

}
