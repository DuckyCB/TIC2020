package uy.edu.um.tic1.entitites.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entitites.cart.CartDTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ClientDTO extends AppUserDTO {

    private CartDTO currentCart;



}
