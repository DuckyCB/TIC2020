package uy.edu.um.tic1.entitites.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entitites.StoreDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class StoreUserDTO extends AppUserDTO{

    private StoreDTO store;

}
