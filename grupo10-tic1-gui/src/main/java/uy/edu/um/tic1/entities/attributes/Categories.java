package uy.edu.um.tic1.entities.attributes;

public class Categories {

    private static final String[] shirts = new String[]{"Manga corta", "Manga larga", "Musculosa"};
    private static final String[] coat = new String[]{"Canguro", "Campera", "Deportivo"};
    private static final String[] pants = new String[]{"Jean", "Joggin"};
    private static final String[] underwearMan = new String[]{"Boxer", "Medias"};
    private static final String[] underwearWoman = new String[]{"Tanga", "Soutien", "Medias"};
    private static final String[] dresses = new String[]{"Largos", "Cortos"};
    private static final String[] skirts = new String[]{"Larga", "Corta"};

    private static final String[] man = {"Remeras", "Buzos", "Pantalones", "Ropa interior"};
    private static final String[] woman = {"Remeras", "Buzos", "Vestidos", "Polleras", "Pantalones", "Ropa interior"};

    private static final String[] genre = {"Hombre", "Mujer", "Niño", "Niña", "Unisex"};


    public static String[] getGenre() {
        return genre;
    }

    /**
     * @param genre Busca la lista asociada al genero
     * @return  Lista de categorias
     */
    public static String[] getSubCategory(String genre) {

        switch (genre) {
            case "Hombre":
            case "H":
            case "Niño":
            case "Unisex":
                return man;
            case "Mujer":
            case"M":
            case "Niña":
                return woman;
        }

        return new String[]{};

    }

    /**
     * @param genre Genero a buscar
     * @param category Categoria a buscar
     * @return Lista de categorias
     */
    public static String[] getSubCategory(String genre, String category) {

        switch (genre) {
            case "Hombre":
            case "H":
            case "Niño":
            case "Unisex":
                switch (category) {
                    case "Remeras":
                        return shirts;
                    case "shirt":
                        return shirts;
                    case "Buzos":
                        return coat;
                    case "hoodie":
                        return coat;
                    case "Pantalones":
                        return pants;
                    case "trousers":
                        return pants;
                    case "Ropa interior":
                        return underwearMan;
                }
            case "Mujer":
            case "M":
            case "Niña":
                switch (category) {
                    case "Remeras":
                        return shirts;
                    case "shirt":
                        return shirts;
                    case "Buzos":
                        return coat;
                    case "hoodie":
                        return coat;
                    case "Vestidos":
                        return dresses;
                    case "Pantalones":
                        return pants;
                    case "trousers":
                        return pants;
                    case "Polleras":
                        return skirts;
                    case "Ropa interior":
                        return underwearWoman;
                }
        }

        return new String[]{};

    }

    /**
     * Funcion que recibe un string de categoria, y lo convierte en un numero
     * @param category Categoria que se desea tomar
     * @return Integer asociado a la categoría
     */
    public static int getIntCategory(String category) {

        switch (category) {
            case "Remeras":
                return 0;
            case "Buzos":
                return 1;
            case "Pantalones":
                return 2;
            case "Shorts":
                return 3;
            case "Vestidos":
                return 4;
            case "Polleras":
                return 5;
            case "Ropa interior":
                return 6;
            default:
                return -1;
        }

    }

    public static String getCategoryFromInt(Integer category) {

        switch (category) {
            case 0:
                return "Remeras";
            case 1:
                return "Buzos";
            case 2:
                return "Pantalones";
            case 3:
                return "Shorts";
            case 4:
                return "Vestidos";
            case 5:
                return "Polleras";
            case 6:
                return "Ropa interior";
            default:
                return "categoría desconocida";
        }

    }

    public static String castCategory(String category) {

        switch (category) {
            case "Remeras":
                return "shirt";
            case "Buzos":
                return "hoodie";
            case "Pantalones":
                return "trousers";

            default:
                return "";
        }

    }



}
