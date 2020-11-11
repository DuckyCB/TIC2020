package uy.edu.um.tic1.entities.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import uy.edu.um.tic1.entitites.users.AdminUserDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.security.user.ApplicationUserRole;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue("admin")
@AllArgsConstructor

@Data
@SuperBuilder
public class AdminUser extends AppUser{


    private final static Set<? extends GrantedAuthority> grantedAuthorities = ApplicationUserRole.ADMIN.getGrantedAuthorities();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AdminUser.grantedAuthorities;
    }

    @Override
    public AppUserDTO toDTO() {
        return AdminUserDTO.builder()
                .id(this.getId())
                .username(this.getUsername())
                .password(this.getPassword())
                .build();
    }


}
