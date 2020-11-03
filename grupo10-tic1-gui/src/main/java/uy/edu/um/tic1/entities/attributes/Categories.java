package uy.edu.um.tic1.entities.attributes;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

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
            case "Niño":
            case "Unisex":
                return man;
            case "Mujer":
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
            case "Niño":
            case "Unisex":
                switch (category) {
                    case "Remeras":
                        return shirts;
                    case "Buzos":
                        return coat;
                    case "Pantalones":
                        return pants;
                    case "Ropa interior":
                        return underwearMan;
                }
            case "Mujer":
            case "Niña":
                switch (category) {
                    case "Remeras":
                        return shirts;
                    case "Buzos":
                        return coat;
                    case "Vestidos":
                        return dresses;
                    case "Pantalones":
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
     *
     * @param category Nombre de la categoria
     * @return Pane de la categoria
     */
    public static Pane getPane(String category) {

        Label label = new Label(category);
        label.setFont(Font.font("Cambria", 16));
        label.setLayoutX(23);
        label.setLayoutY(3);

        Pane pane = new Pane();
        pane.setPrefSize(200, 30);
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: #e2e2e2");

        return pane;

    }
}
