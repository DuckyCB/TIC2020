package uy.edu.um.tic1.security.user;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import uy.edu.um.tic1.security.user.ApplicationUserPermission;

import java.util.Set;
import java.util.stream.Collectors;

import static uy.edu.um.tic1.security.user.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Set.of(BRAND_READ, BRAND_WRITE,
            STORE_READ, STORE_WRITE,
            PRODUCT_READ, PRODUCT_WRITE,
            CLIENT_READ, CLIENT_WRITE,
            STOCK_READ, STOCK_WRITE)),

    BRAND(Set.of(BRAND_READ,
            STORE_READ, STORE_WRITE,
            PRODUCT_READ, PRODUCT_WRITE)),

    STORE(Set.of(BRAND_READ,
            STORE_READ,
            PRODUCT_READ,
            STOCK_READ, STOCK_WRITE)),

    CLIENT(Set.of(BRAND_READ,
            STORE_READ,
            PRODUCT_READ,
            STOCK_READ, STORE_WRITE));


    private final Set<ApplicationUserPermission> permissions;


    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }


    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }

}
