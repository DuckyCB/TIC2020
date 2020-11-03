package uy.edu.um.tic1.requests;

import javafx.scene.image.ImageView;

public class RequestMain {

    public static ImageView getBanner() {

        return new ImageView("/uy/edu/um/tic1/images/Banner/teens.jpg");

    }

    public static String[] getBrands() {

        return new String[]{"Levi's", "Linus Tech Tips", "SiSi", "Haleiwa", "El Juanito"};

    }
}
