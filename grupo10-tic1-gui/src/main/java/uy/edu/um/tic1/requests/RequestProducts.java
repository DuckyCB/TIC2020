package uy.edu.um.tic1.requests;

import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.product.ProductDTO;

/**
 * @deprecated
 */
public class RequestProducts {

    private static String queryString = "/products/?";
    private static Boolean queryFirst = Boolean.TRUE;

    private static void setQueryString(String query) {

        if (queryFirst) {

            queryString += query;
            queryFirst = false;

        } else {

            queryString += "&" + query;

        }

    }


    public static void queryReset() {

        queryString = "/products/?";

    }

    private static void searchInServer() {

    }


    public static Product[] getAll() {

        System.out.println("Todos los productos");

        return new Product[]{};

    }

    /**
     * @param genre Atributo de genero por el que buscar
     * @return Lista de productos
     */
    public static Product[] getByGenre(String genre) {

        char genreChar;
        if (genre.equals("Hombre")) {
            genreChar = 'M';
        } else {
            genreChar = 'F';
        }
        String text = "genre=\""+genreChar+"\"";
        setQueryString(text);

        searchInServer();

        return new Product[]{};

    }

    /**
     * @param category Atributo de categoria por el que buscar
     * @return Lista de productos
     */
    public static Product[] getByCategory(String category) {

        String text = "category=\""+category+"\"";
        setQueryString(text);

        searchInServer();
        return new Product[]{};

    }

    /**
     *
     * @param subcategory Atributo de subcategoria por el que buscar
     * @return Lista de productos
     */
    public static Product[] getBySubCategory(String subcategory) {

        String text = "subcategory=\""+subcategory+"\"";
        setQueryString(text);

        searchInServer();
        return new Product[]{};

    }

    /**
     *
     * @param name String de la busqueda realizada, busca en los nombres
     * @return Lista de productos
     */
    public static Product[] getByName(String name){

        String text = "name=\""+name+"\"";
        setQueryString(text);

        searchInServer();
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
