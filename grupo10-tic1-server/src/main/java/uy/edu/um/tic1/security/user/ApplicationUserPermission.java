package uy.edu.um.tic1.security.user;




public enum ApplicationUserPermission {

    BRAND_WRITE("brand:write"),
    BRAND_READ("brand:read"),

    STORE_WRITE("store:write"),
    STORE_READ("store:read"),

    CLIENT_WRITE("client:write"),
    CLIENT_READ("client:read"),

    PRODUCT_WRITE("product:write"),
    PRODUCT_READ("product:read"),

    STOCK_WRITE("stock:write"),
    STOCK_READ("stock:read");


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }



    public String getPermission() {
        return permission;
    }
}
