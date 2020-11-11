package uy.edu.um.tic1.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import uy.edu.um.tic1.entities.Store;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.entitites.users.StoreUserDTO;
import uy.edu.um.tic1.security.user.ApplicationUserRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue("store")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class StoreUser extends AppUser{

    private final static Set<? extends GrantedAuthority> grantedAuthorities = ApplicationUserRole.STORE.getGrantedAuthorities();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return StoreUser.grantedAuthorities;
    }

    @OneToOne
    @JoinColumn(name = "store",
            foreignKey = @ForeignKey(name = "fk_store_user_store")
    )
    private Store store;


    @Override
    public AppUserDTO toDTO() {
        return StoreUserDTO.builder()
                .id(this.getId())
                .username(this.getUsername())
                .password(this.getPassword())
                .store(this.store.toDTO())
                .build();
    }
}
