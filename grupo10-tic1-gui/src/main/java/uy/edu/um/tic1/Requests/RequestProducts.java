package uy.edu.um.tic1.Requests;

import uy.edu.um.tic1.product.Product;

public class RequestProducts {

    public static Product[] getAll() {

        System.out.println("Todos los productos");
        return new Product[]{};

    }

    /**
     *
     * @param genre Atributo de genero por el que buscar
     * @return Lista de productos
     */
    public static Product[] getByCategory(String genre) {

        System.out.println("Busca productos de" + genre);
        return new Product[]{};

    }

    /**
     *
     * @param genre Atributo de genero por el que buscar
     * @param category Atributo de categoria por el que buscar
     * @return Lista de productos
     */
    public static Product[] getByCategory(String genre, String category) {

        System.out.println("Busca productos de" + genre + " - " + category);
        return new Product[]{};

    }

    /**
     *
     * @param genre Atributo de genero por el que buscar
     * @param category Atributo de categoria por el que buscar
     * @param subcategory Atributo de subcategoria por el que buscar
     * @return Lista de productos
     */
    public static Product[] getByCategory(String genre, String category, String subcategory) {

        System.out.println("Busca productos de" + genre + " - " + category + " - " + subcategory);
        return new Product[]{};

    }

    /**
     *
     * @param name String de la busqueda realizada, busca en los nombres
     * @return Lista de productos
     */
    public static Product[] getByName(String name){

        System.out.println("Busca productos con el nombre "+name);
        return new Product[]{};

    }

    /**
     * Busqueda de productos dentro de un rango de precio
     * @param min Precio minimo
     * @param max Precio maximo
     * @return Lista de productos
     */
    public static Product[] getByPrice(Float min, Float max){

        System.out.println("Busca productos entre "+min+" y "+max);
        return new Product[]{};

    }

    /**
     * Busqueda de productos con precio minimo
     * @param min Precio minimo
     * @return Lista de productos
     */
    public static Product[] getByMinPrice(Float min){

        System.out.println("Busca productos de mas de "+min);
        return new Product[]{};

    }

    /**
     * Busqueda de productos con precio maximo
     * @param max Precio maximo
     * @return Lista de productos
     */
    public static Product[] getByMaxPrice(Float max){

        System.out.println("Busca productos de menos de "+max);
        return new Product[]{};

    }

    public static Product[] getSortedByLowFirst(){

        System.out.println("Busca productos ordenados de menor a mayor precio");
        return new Product[]{};

    }

    public static Product[] getSortedByHighFirst(){

        System.out.println("Busca productos ordenados de mayor a menor precio");
        return new Product[]{};

    }

    public static Product[] getCart(String user) {

        System.out.println("Recupera el carrito para el usuario "+user);
        return new Product[]{};

    }

}
