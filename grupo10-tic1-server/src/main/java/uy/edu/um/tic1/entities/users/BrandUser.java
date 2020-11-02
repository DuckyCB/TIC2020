package uy.edu.um.tic1.entities.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import uy.edu.um.tic1.entities.Brand;

import uy.edu.um.tic1.entitites.users.AdminUserDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.BrandUserDTO;
import uy.edu.um.tic1.security.user.ApplicationUserRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue("brand")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class BrandUser extends AppUser{


    private final static Set<? extends GrantedAuthority> grantedAuthorities = ApplicationUserRole.BRAND.getGrantedAuthorities();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return BrandUser.grantedAuthorities;
    }

    @OneToOne
    @JoinColumn(name = "brand",
            foreignKey = @ForeignKey(name = "fk_brand_user_brand")
    )
    private Brand brand;


    @Override
    public AppUserDTO toDTO() {
        return BrandUserDTO.builder()
                .id(this.getId())
                .username(this.getUsername())
                .brand(this.getBrand().toDTO())
                .build();
    }

}
