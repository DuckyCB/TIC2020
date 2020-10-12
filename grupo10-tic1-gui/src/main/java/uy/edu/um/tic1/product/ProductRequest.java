package uy.edu.um.tic1.product;

import uy.edu.um.tic1.entity.ColorRGB;

public class ProductRequest {

    public static Products[] productsList;

    public static Products[] listPants() {

        Products[] list = new Products[3];
        list[0] = new Products("/uy/edu/um/tic1/images/Men/Jean/black.jpg", "Jean Black",
                "Levi's", 499.99f,
                new String[]{"XS","M","L"},
                new ColorRGB[]{new ColorRGB(0.0f, 0.0f, 0.0f)});
        list[1] = new Products("/uy/edu/um/tic1/images/Men/Jean/blue.jpg", "Jean Blue",
                "Levi's", 449.99f,
                new String[]{"S","M","XL"},
                new ColorRGB[]{new ColorRGB(0.0f, 0.0f, 1.0f)});
        list[2] = new Products("/uy/edu/um/tic1/images/Men/Jean/lightBlue.jpg", "Jean Light Blue",
                "Levi's", 429.99f,
                new String[]{"S","M","L"},
                new ColorRGB[]{new ColorRGB(0.5f, 0.5f, 1.0f)});
        return list;

    }

    public static Products[] listShirts() {

        Products[] list = new Products[4];
        list[0] = new Products("/uy/edu/um/tic1/images/Men/Shirt/black.jpg", "Camisa Black",
                "Levi's", 389.99f,
                new String[]{"XS","M","L"},
                new ColorRGB[]{new ColorRGB(0.0f, 0.0f, 0.0f)});
        list[1] = new Products("/uy/edu/um/tic1/images/Men/Shirt/brown.jpg", "Camisa Orange",
                "Levi's", 349.99f,
                new String[]{"XS","M","L", "XL"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f),
                        new ColorRGB(1.0f, 0.0f, 0.0f), new ColorRGB(0.0f, 1.0f, 0.0f),
                        new ColorRGB(0.0f, 0.0f, 1.0f), new ColorRGB(1.0f, 1.0f, 0.0f),
                        new ColorRGB(0.0f, 1.0f, 1.0f)});
        list[2] = new Products("/uy/edu/um/tic1/images/Men/Shirt/green.jpg", "Remera Green",
                "Levi's", 299.99f,
                new String[]{"S","M","XL"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f)});
        list[3] = new Products("/uy/edu/um/tic1/images/Men/Shirt/pink.jpg", "Remera Pink",
                "Levi's", 249.99f,
                new String[]{"S","M","L"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f)});
        return list;

    }

    public static Products[] listBuzos() {

        Products[] list = new Products[3];
        list[0] = new Products("/uy/edu/um/tic1/images/Men/Sweatshirt/black.jpg", "Pullover Black Leaves",
                "Levi's", 699.99f,
                new String[]{"S","M","L"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f)});
        list[1] = new Products("/uy/edu/um/tic1/images/Men/Sweatshirt/lightBlue.jpg", "Pullover Light Blue",
                "Levi's", 399.99f,
                new String[]{"S","M","L"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f)});
        list[2] = new Products("/uy/edu/um/tic1/images/Men/Sweatshirt/white.jpg", "Canguro White",
                "Levi's", 599.99f,
                new String[]{"S","M","L"},
                new ColorRGB[]{new ColorRGB(1.0f, 1.0f, 1.0f), new ColorRGB(0.0f, 0.0f, 0.0f)});
        return list;

    }

    public static Products[] listAll() {

        Products[] list = new Products[10];

        Products[] pants = listPants();
        for (int i = 0; i < 3; i++) {
            list[i] = pants[i];
        }
        Products[] shirts = listShirts();
        for (int i = 0; i < 4; i++) {
            list[i+3] = shirts[i];
        }
        Products[] buzos = listBuzos();
        for (int i = 0; i < 3; i++) {
            list[i+7] = buzos[i];
        }

        return list;

    }

}
