package uy.edu.um.tic1.entitites.users;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import uy.edu.um.tic1.entitites.BrandDTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class BrandUserDTO extends AppUserDTO{

    private BrandDTO brand;


}
