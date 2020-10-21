package uy.edu.um.tic1.security;




public enum ApplicationUserPermission {

    BRAND_WRITE("brand:write"),
    BRAND_READ("brand:read"),

    STORE_WRITE("store:write"),
    STORE_READ("store:read"),

    CLIENT_WRITE("client:write"),
    CLIENT_READ("client:read"),

    PRODUCT_WRITE("product:write"),
    PRODUCT_READ("product:read");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }



    public String getPermission() {
        return permission;
    }
}
