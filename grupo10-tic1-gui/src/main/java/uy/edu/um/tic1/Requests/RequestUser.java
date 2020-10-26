package uy.edu.um.tic1.Requests;

import org.springframework.beans.factory.annotation.Autowired;
import uy.edu.um.tic1.StoreApplication;

public class RequestUser {

    public static void getUser(String user, String password, StoreApplication storeApplication) {

        if ( (user.equals("brand")) && (password.equals("1234")) ) {
            storeApplication.sceneAdminBrand();
        }
        if ( (user.equals("store")) && (password.equals("1234")) ) {
            storeApplication.sceneAdminStore();
        }

    }
}
