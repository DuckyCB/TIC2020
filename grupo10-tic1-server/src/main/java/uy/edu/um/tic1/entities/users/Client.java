package uy.edu.um.tic1.entities.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import uy.edu.um.tic1.entities.cart.Cart;
import uy.edu.um.tic1.entities.SizeAndColor;
import uy.edu.um.tic1.entities.contact.Address;
import uy.edu.um.tic1.entities.contact.Email;
import uy.edu.um.tic1.entities.contact.TelephoneNumber;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.contact.AddressDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.BrandUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.security.user.ApplicationUserRole;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
@DiscriminatorValue("client")
@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
public class Client extends AppUser{

    private final static Set<? extends GrantedAuthority> grantedAuthorities = ApplicationUserRole.CLIENT.getGrantedAuthorities();

    @Column(length = 20)
    private String firstName;
    @Column(length = 20)
    private String lastName;

    @Embedded
    private Email email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "current_cart",
            foreignKey = @ForeignKey(name = "fk_client_cart")
    )
    private Cart currentCart;

    private Address address;

    private TelephoneNumber telephoneNumber;



    private void addToCart(Product product, SizeAndColor sizeAndColor, Integer quantity){

        if(this.currentCart == null) {
            this.currentCart = Cart.builder()
                    .client(this)
                    .build();
        }

        this.currentCart.addToCart(product, sizeAndColor, quantity);

    }

    public void processCurrentCart(Boolean toDeliver){

        if (this.currentCart != null)
            this.currentCart.processCart(toDeliver);


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return Client.grantedAuthorities;
    }

    @Override
    public ClientDTO toDTO() {
        ClientDTO client =  ClientDTO.builder()
                .id(this.getId())
                .firstName(this.firstName)
                .lastName(this.lastName)
                .username(this.getUsername())
                .address(this.address.toDTO())
                .currentCart(null)
                .telephoneNumber(this.telephoneNumber.toDTO())
                .build();


        if (this.currentCart != null){
            client.setCurrentCart(this.currentCart.toDTO());
        }
        return client;
    }

    public void buyCurrentCart(Boolean toDeliver){
        if (currentCart != null){

            //TODO: FACTURAR IMPORTE --> Se va a facturar importe redireccionando la factura a un medio de pago online
            this.currentCart.processCart(toDeliver);
        }
    }

}
