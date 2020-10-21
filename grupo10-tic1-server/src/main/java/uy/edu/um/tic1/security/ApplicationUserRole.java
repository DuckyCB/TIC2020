package uy.edu.um.tic1.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static uy.edu.um.tic1.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    ADMIN(Sets.newHashSet(BRAND_READ, BRAND_WRITE,
            STORE_READ, STORE_WRITE,
            PRODUCT_READ, PRODUCT_WRITE,
            CLIENT_READ, CLIENT_WRITE)),

    BRAND(Sets.newHashSet(BRAND_READ,
            STORE_READ, STORE_WRITE,
            PRODUCT_READ, PRODUCT_WRITE)),

    STORE(Sets.newHashSet(BRAND_READ,
            STORE_READ,
            PRODUCT_READ)),

    CLIENT(Sets.newHashSet(BRAND_READ,
            STORE_READ,
            PRODUCT_READ));


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
