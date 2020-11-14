package uy.edu.um.tic1.requests;

import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.products.Product;

/**
 * @deprecated
 */
public class ProductRequestOld {

    public static Product[] productsList;

    /** Genera y devuelve una lista con todos los pantalones */
    public static Product[] getPants() {

        Product[] list = new Product[3];
        list[0] = new Product("/uy/edu/um/tic1/images/Men/Jean/black.jpg", "Jean Black",
                "Levi's", 499.99f,
                new String[]{"XS","M","L"},
                new String[]{Colors.getBlack(), Colors.getGrey()});
        list[1] = new Product("/uy/edu/um/tic1/images/Men/Jean/blue.jpg", "Jean Blue",
                "Levi's", 449.99f,
                new String[]{"S","M","XL"},
                new String[]{Colors.getBlue()});
        list[2] = new Product("/uy/edu/um/tic1/images/Men/Jean/lightBlue.jpg", "Jean Light Blue",
                "Levi's", 429.99f,
                new String[]{"S","M","L"},
                new String[]{Colors.getLightBlue()});
        return list;

    }

    /** Genera y devuelve una lista con todos los remeras */
    public static Product[] getShirts() {

        Product[] list = new Product[4];
        list[0] = new Product("/uy/edu/um/tic1/images/Men/Shirt/black.jpg", "Camisa Black",
                "Levi's", 389.99f,
                new String[]{"XS","M","L"},
                new String[]{Colors.getBlack(), Colors.getDarkGreen()});
        list[1] = new Product("/uy/edu/um/tic1/images/Men/Shirt/brown.jpg", "Camisa Orange",
                "Levi's", 349.99f,
                new String[]{"XS","M","L", "XL"},
                new String[]{Colors.getBrown(), Colors.getBlue(), Colors.getOrange(), Colors.getRed()});
        list[2] = new Product("/uy/edu/um/tic1/images/Men/Shirt/green.jpg", "Remera Green",
                "Levi's", 299.99f,
                new String[]{"S","M","XL"},
                new String[]{Colors.getDarkGreen(), Colors.getLightGreen()});
        list[3] = new Product("/uy/edu/um/tic1/images/Men/Shirt/pink.jpg", "Remera Pink",
                "Levi's", 249.99f,
                new String[]{"S","M","L"},
                new String[]{Colors.getPink(), Colors.getPurple()});
        return list;

    }

    /** Genera y devuelve una lista con todos los buzos */
    public static Product[] getBuzos() {

        Product[] list = new Product[3];
        list[0] = new Product("/uy/edu/um/tic1/images/Men/Sweatshirt/black.jpg", "Pullover Black Leaves",
                "Levi's", 699.99f,
                new String[]{"S","M","L"},
                new String[]{Colors.getBlack(), Colors.getDarkGreen()});
        list[1] = new Product("/uy/edu/um/tic1/images/Men/Sweatshirt/lightBlue.jpg", "Pullover Light Blue",
                "Levi's", 399.99f,
                new String[]{"S","M","L"},
                new String[]{Colors.getBlue(), Colors.getLightBlue()});
        list[2] = new Product("/uy/edu/um/tic1/images/Men/Sweatshirt/white.jpg", "Canguro White",
                "Levi's", 599.99f,
                new String[]{"S","M","L"},
                new String[]{Colors.getWhite(), Colors.getGrey()});
        return list;

    }

    /** Genera y devuelve una lista de todas las categorias disponibles */
    public static Product[] getAll() {

        Product[] list = new Product[10];

        Product[] pants = getPants();
        for (int i = 0; i < 3; i++) {
            list[i] = pants[i];
        }
        Product[] shirts = getShirts();
        for (int i = 0; i < 4; i++) {
            list[i+3] = shirts[i];
        }
        Product[] coats = getBuzos();
        for (int i = 0; i < 3; i++) {
            list[i+7] = coats[i];
        }

        return list;

    }

}
