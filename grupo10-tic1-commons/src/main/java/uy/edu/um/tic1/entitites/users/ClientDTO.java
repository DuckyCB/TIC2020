package uy.edu.um.tic1.entitites.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.contact.AddressDTO;
import uy.edu.um.tic1.entitites.contact.TelephoneNumberDTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientDTO extends AppUserDTO {


    private String firstName;

    private String lastName;

    private CartDTO currentCart;

    private AddressDTO address;

    private TelephoneNumberDTO telephoneNumber;



}
