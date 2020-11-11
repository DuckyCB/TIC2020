package uy.edu.um.tic1.entities.users;



import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import uy.edu.um.tic1.entities.Brand;
import uy.edu.um.tic1.entities.products.Hoodie;
import uy.edu.um.tic1.entities.products.Shirt;
import uy.edu.um.tic1.entities.products.Trousers;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "user_role")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdminUser.class, name = "admin"),
        @JsonSubTypes.Type(value = BrandUser.class, name = "brand"),
        @JsonSubTypes.Type(value = StoreUser.class, name = "store"),
        @JsonSubTypes.Type(value = Client.class, name = "client"),
})


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="user_role",
        discriminatorType = DiscriminatorType.STRING)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class AppUser implements UserDetails {



    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Getter @Setter
    private Integer id;

    @Column(name = "username",
        length = 20,
        unique = true)
    private String username;


    private String password;


    private final static Set<? extends GrantedAuthority> grantedAuthorities = null;

    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;


    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return AppUser.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }


    public abstract AppUserDTO toDTO();


    public void initUser(){
        this.isAccountNonExpired = true;
        this.isAccountNonLocked = true;
        this.isCredentialsNonExpired = true;
        this.isEnabled = true;
    }
}
