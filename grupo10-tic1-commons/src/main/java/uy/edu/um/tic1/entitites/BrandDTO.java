package uy.edu.um.tic1.entitites;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import uy.edu.um.tic1.entitites.contact.EmailDTO;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandDTO {



    private Integer id;


    private String name;

    private EmailDTO email;
}